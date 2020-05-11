package com.robin.authserver.security.dto;

import com.robin.authserver.security.entity.UcClients;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.StringUtils;

import java.util.*;

public class MyClientsPrincipal implements ClientDetails {

    private UcClients ucClients;

    public MyClientsPrincipal(UcClients ucClients) {
        this.ucClients = ucClients;
    }

    @Override
    public String getClientId() {
        return ucClients.getClientId();
    }

    //TODO ResourceIDs
    @Override
    public Set<String> getResourceIds() {
        return new HashSet<>(Arrays.asList(ucClients.getResourceIds().split(",")));
    }

    @Override
    public boolean isSecretRequired() {
        return !StringUtils.isEmpty(ucClients.getClientSecret());
    }

    @Override
    public String getClientSecret() {
        return ucClients.getClientSecret();
    }

    @Override
    public boolean isScoped() {
        return !StringUtils.isEmpty(ucClients.getScope());
    }

    @Override
    public Set<String> getScope() {
        return new HashSet<>(Arrays.asList(ucClients.getScope().split(",")));
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return new HashSet<>(Arrays.asList(ucClients.getAuthorizedGrantTypes().split(",")));
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return new HashSet<>(Arrays.asList(ucClients.getRegisteredRedirectUri().split(",")));
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_CLIENT"));
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return ucClients.getAccessTokenValiditySeconds();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return ucClients.getRefreshTokenValiditySeconds();
    }

    @Override
    public boolean isAutoApprove(String s) {
        return true;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        Map<String, Object> ad = new HashMap();
        ad.put("clientName", ucClients.getClientName());
        return Collections.unmodifiableMap(ad);
    }
}
