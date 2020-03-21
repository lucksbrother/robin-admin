package com.robin.authserver.security.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class UcUsers {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private Long connectionId;
  private String email;
  private Long emailVerified;
  private String phoneNumber;
  private Long phoneVerified;
  private String displayName;
  private String nickname;
  private String givenName;
  private String familyName;
  private String middleName;
  private String avatarUrl;
  private String password;
  private Long passwordStrength;
  private Long enabled;
  private Long locked;
  private Long type;
  private String source;
  private Date lastLoginAt;
  private String gender;
  private String birthDate;
  private String zoneInfo;
  private String locale;
  private String website;
  private String address;
  private String metadata;
  private Date createdAt;
  private Date updatedAt;
  private String externalSource;
  private String externalId;
  private String regClientId;
  private Long version;


  

}
