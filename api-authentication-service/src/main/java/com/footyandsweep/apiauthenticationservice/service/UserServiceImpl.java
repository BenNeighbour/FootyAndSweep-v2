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

package com.footyandsweep.apiauthenticationservice.service;

import com.footyandsweep.apiauthenticationservice.dao.SweepstakeIdDao;
import com.footyandsweep.apiauthenticationservice.dao.UserDao;
import com.footyandsweep.apiauthenticationservice.exception.SignUpException;
import com.footyandsweep.apiauthenticationservice.model.User;
import com.footyandsweep.apiauthenticationservice.payload.SignUpRequest;
import com.footyandsweep.apiauthenticationservice.relation.SweepstakeIds;
import com.footyandsweep.apicommonlibrary.exceptions.UserDoesNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
  public void saveSweepstakeId(UUID sweepstakeId, UUID participantId) {
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
  public void addOwnerToSweepstake(UUID sweepstakeId, UUID ownerId) {
    User addingParticipant = userDao.findUserById(ownerId);

    if (addingParticipant != null) {
      sweepstakeIdDao.save(new SweepstakeIds(ownerId, sweepstakeId));
    } else {
      /* Throw a user does not exist error */
      throw new UserDoesNotExistException();
    }
  }

  @Override
  public void deleteAllSweepstakeRelations(UUID sweepstakeId) {
    Optional<List<SweepstakeIds>> sweepstakeIdsList =
            sweepstakeIdDao.findAllSweepstakeIdsBySweepstakeId(sweepstakeId);

    assert sweepstakeIdsList.isPresent();
    sweepstakeIdsList.get().forEach(sweepstakeIdDao::delete);
  }
}
