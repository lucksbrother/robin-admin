package com.robin.authserver.security.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class UcUsers extends BaseEntity{

  private String username;
  private String password;
  private Long enabled;
  private Long locked;

}
