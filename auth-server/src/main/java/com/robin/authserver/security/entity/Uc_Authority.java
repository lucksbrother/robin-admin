package com.robin.authserver.security.entity;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Uc_Authority extends BaseEntity {

    private String authName;
    private String authDesc;
    private Boolean isEnable;

}
