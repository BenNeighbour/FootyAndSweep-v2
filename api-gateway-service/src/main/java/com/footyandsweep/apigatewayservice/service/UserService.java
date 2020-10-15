package com.footyandsweep.apigatewayservice.service;

import com.footyandsweep.apicommonlibrary.model.SweepstakeCommon;
import com.footyandsweep.apigatewayservice.model.User;

import java.util.UUID;

public interface UserService {

    User addUserToSweepstake(UUID userId, SweepstakeCommon sweepstake);

}
