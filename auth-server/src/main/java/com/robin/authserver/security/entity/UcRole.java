package com.robin.authserver.security.entity;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class UcRole extends BaseEntity{

    private String roleName;
    private String roleDesc;
    private Boolean isEnable;
}
