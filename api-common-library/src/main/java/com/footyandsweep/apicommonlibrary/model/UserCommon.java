package com.footyandsweep.apicommonlibrary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Embeddable
public class UserCommon implements Serializable {

  private static final long serialVersionUID = -8782116311771480122L;

  @Id
  @GeneratedValue
  @Column(columnDefinition = "uuid", updatable = false, name = "id")
  private UUID userId;

  private String name;

  private String password;

  @Embedded private AuthProvider provider;

  enum AuthProvider {
    local,
    facebook,
    google
  }
}
