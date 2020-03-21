package com.robin.authserver.security.dto;

import com.robin.authserver.security.entity.UcUsers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUsersPrincipal implements UserDetails {

    private UcUsers ucUsers;

    public MyUsersPrincipal(UcUsers ucUsers) {
        this.ucUsers = ucUsers;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
    }

    @Override
    public String getPassword() {
        return ucUsers.getPassword();
    }

    @Override
    public String getUsername() {
        return ucUsers.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return ucUsers.getLocked().equals(0L);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return ucUsers.getEnabled().equals(1L);
    }
}
