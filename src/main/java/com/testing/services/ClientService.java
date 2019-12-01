package com.testing.services;

import com.testing.api.mapping.ClientApiClientMapperImpl;
import com.testing.api.resource.ClientApi;
import com.testing.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    ClientApiClientMapperImpl clientApiClientMapper = new ClientApiClientMapperImpl();

    public List<ClientApi> getClients() {
        return (List) clientRepository.findAll();
    }

    public void addClient(ClientApi client) {
        clientRepository.save(clientApiClientMapper.clientApiToClientDto(client));
    }

    public ClientApi getClientInformation(long id) {
        return clientApiClientMapper.clientDtoToClientApi(clientRepository.findById(id).get());
    }
}
