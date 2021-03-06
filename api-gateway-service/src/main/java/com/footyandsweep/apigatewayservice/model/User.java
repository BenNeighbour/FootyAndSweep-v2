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

package com.footyandsweep.apigatewayservice.model;

import com.footyandsweep.apicommonlibrary.model.user.UserCommon;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "user_account")
public class User extends UserCommon {

  @NotNull
  @Enumerated(EnumType.STRING)
  private AuthProvider provider;

  @Lob
  @Column(name = "profilePicture", length = 100000)
  private String profilePicture;

  @Column(name = "providerId")
  private String providerId;

  @Column(name = "isEmailVerified", nullable = false)
  private Boolean isEmailVerified = false;
}
