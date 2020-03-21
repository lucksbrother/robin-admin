package com.robin.authserver.security.repo;


import com.robin.authserver.security.entity.UcUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UcUsersRepo extends JpaRepository<UcUsers,Long> {

    UcUsers findByUsername(String username);
}
