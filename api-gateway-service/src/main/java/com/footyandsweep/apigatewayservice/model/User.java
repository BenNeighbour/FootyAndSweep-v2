package com.footyandsweep.apigatewayservice.model;

import com.footyandsweep.apicommonlibrary.model.UserCommon;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "user_account")
public class User extends UserCommon {
}
