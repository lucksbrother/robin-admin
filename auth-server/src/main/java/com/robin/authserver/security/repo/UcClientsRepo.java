package com.robin.authserver.security.repo;

import com.robin.authserver.security.entity.UcClients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UcClientsRepo extends JpaRepository<UcClients,Long> {

    UcClients findUcClientsByClientId(String clientId);
}
