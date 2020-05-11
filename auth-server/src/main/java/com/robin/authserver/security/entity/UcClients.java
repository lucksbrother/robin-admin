package com.robin.authserver.security.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.*;

/*
create table uc_clients
(
	id bigint unsigned auto_increment
		primary key,
	client_id varchar(128) not null,
	user_id bigint(128) unsigned null,
	client_name varchar(100) null comment '应用户名',
	client_secret varchar(100) null comment '应用户名',
	client_url varchar(2000) null comment '应用地址',
	client_logo_url varchar(2000) null comment '应用logo',
	registered_redirect_uri varchar(6000) null comment '注册会跳地址',
	authorized_grant_types varchar(6000) null comment '授权类型',
	resource_ids varchar(128) null comment '资源id',
	scope varchar(1024) null comment '范围',
	extra varchar(1024) null comment '用户额外信息,建议json字符串',
	created_at datetime not null,
	updated_at datetime not null,
	access_token_validity_seconds bigint null,
	refresh_token_validity_seconds bigint null
)
comment '用户表';
 */

@Data
@Entity
public class UcClients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientId;
    private String clientName;
    private String clientSecret;
    private String registeredRedirectUri;
    private String AuthorizedGrantTypes;
    private String resourceIds;
    private String scope;
    private Date createdAt;
    private Date updatedAt;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
}
