package com.footyandsweep.apigatewayservice.service;

import com.footyandsweep.apicommonlibrary.model.SweepstakeCommon;
import com.footyandsweep.apigatewayservice.dao.SweepstakeIdDao;
import com.footyandsweep.apigatewayservice.dao.UserDao;
import com.footyandsweep.apigatewayservice.model.User;
import com.footyandsweep.apigatewayservice.relation.SweepstakeIds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

  @Autowired private UserDao userDao;

  @Autowired private SweepstakeIdDao sweepstakeIdDao;

  @Override
  public User addUserToSweepstake(UUID userId, SweepstakeCommon sweepstake) {
    try {
      User addingParticipant = userDao.findUserByUserId(userId);

      if (addingParticipant != null) {
        if (sweepstakeIdDao.findSweepstakeIdsByParticipantId(userId) == null) {
          sweepstakeIdDao.save(new SweepstakeIds(userId, sweepstake.getSweepstakeId()));
          return addingParticipant;
        }
      }
    } catch (Exception e) {
      // TODO: THROW BACK ERROR MESSGAE!!
      return null;
    }

    return null;
  }
}
