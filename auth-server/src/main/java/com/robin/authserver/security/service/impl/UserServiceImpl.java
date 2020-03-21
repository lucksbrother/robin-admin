package com.robin.authserver.security.service.impl;

import com.robin.authserver.security.dto.MyUsersPrincipal;
import com.robin.authserver.security.entity.UcUsers;
import com.robin.authserver.security.repo.UcUsersRepo;
import com.robin.authserver.security.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UcUsersRepo ucUsersRepo;

    public UserServiceImpl(UcUsersRepo ucUsersRepo) {
        this.ucUsersRepo = ucUsersRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UcUsers ucUsers = ucUsersRepo.findByUsername(s);
        if (ucUsers == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return new MyUsersPrincipal(ucUsers);
    }

}
