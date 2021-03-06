/*
 *   Copyright 2021 FootyAndSweep
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.footyandsweep.apigatewayservice.service;

import com.footyandsweep.apigatewayservice.dao.SweepstakeIdDao;
import com.footyandsweep.apigatewayservice.dao.UserDao;
import com.footyandsweep.apigatewayservice.exception.SignUpException;
import com.footyandsweep.apigatewayservice.model.User;
import com.footyandsweep.apigatewayservice.payload.SignUpRequest;
import com.footyandsweep.apigatewayservice.relation.SweepstakeIds;
import com.footyandsweep.apicommonlibrary.exceptions.InsufficientCreditsException;
import com.footyandsweep.apicommonlibrary.exceptions.UserDoesNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");

  private final UserDao userDao;
  private final SweepstakeIdDao sweepstakeIdDao;

  public UserServiceImpl(final UserDao userDao, final SweepstakeIdDao sweepstakeIdDao) {
    this.userDao = userDao;
    this.sweepstakeIdDao = sweepstakeIdDao;
  }

  @Override
  public void checkSignUpRequestIsValid(SignUpRequest request) throws SignUpException {
    /* Firstly, check if the confirmPassword string and password string are the same, or they are not already signed up */
    if (!request.getPassword().equals(request.getConfirmPassword())) {
      throw new SignUpException("Password and Confirm Password do not match up!");
    } else if (userDao.existsByEmail(request.getEmail())) {
      throw new SignUpException(
          "Another account is using this email address. Please log into your account.");
    }
  }

  @Override
  public void saveSweepstakeId(String sweepstakeId, String participantId) {
    /* Check if the user actually exists */
    User joiningUser = userDao.findUserById(participantId);

    if (joiningUser != null) {
      /* If the user exists, save the new relation */
      SweepstakeIds sweepstakeIds = new SweepstakeIds(participantId, sweepstakeId);
      sweepstakeIdDao.save(sweepstakeIds);
    } else {
      throw new UserDoesNotExistException();
    }
  }

  @Override
  public void addOwnerToSweepstake(String sweepstakeId, String ownerId) {
    User addingParticipant = userDao.findUserById(ownerId);

    if (addingParticipant != null) {
      sweepstakeIdDao.save(new SweepstakeIds(ownerId, sweepstakeId));
    } else {
      /* Throw a user does not exist error */
      throw new UserDoesNotExistException();
    }
  }

  @Override
  public void deleteAllSweepstakeRelations(String sweepstakeId) {
    Optional<List<SweepstakeIds>> sweepstakeIdsList =
        sweepstakeIdDao.findAllSweepstakeIdsBySweepstakeId(sweepstakeId);

    assert sweepstakeIdsList.isPresent();
    sweepstakeIdsList.get().forEach(sweepstakeIdDao::delete);
  }

  @Override
  public void updateUserBalance(String userId, BigDecimal amountDeducted) {
    try {
      User user = userDao.findUserById(userId);

      if (user == null) throw new UserDoesNotExistException();

      /* Check if the user can afford the amount deducted */
      if (!(user.getBalance().add(amountDeducted).compareTo(new BigDecimal("0")) >= 0))
        throw new InsufficientCreditsException();

      user.setBalance(user.getBalance().add(amountDeducted));
      userDao.saveAndFlush(user);
    } catch (UserDoesNotExistException | NullPointerException e) {
      throw new UserDoesNotExistException();
    } catch (InsufficientCreditsException e) {
      throw new InsufficientCreditsException();
    }
  }
}
