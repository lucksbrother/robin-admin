package com.robin.authserver.security.service.impl;

import com.robin.authserver.security.dto.MyClientsPrincipal;
import com.robin.authserver.security.entity.UcClients;
import com.robin.authserver.security.repo.UcClientsRepo;
import com.robin.authserver.security.service.ClientService;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private final UcClientsRepo ucClientsRepo;

    public ClientServiceImpl(UcClientsRepo ucClientsRepo) {
        this.ucClientsRepo = ucClientsRepo;
    }

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        UcClients client = ucClientsRepo.findUcClientsByClientId(s);
        if (client == null) {
            throw new NoSuchClientException("客户端不存在");
        }
        return new MyClientsPrincipal(client);
    }

}
