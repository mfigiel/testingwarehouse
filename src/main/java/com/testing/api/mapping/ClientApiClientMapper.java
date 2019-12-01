package com.testing.api.mapping;

import com.testing.api.resource.ClientApi;
import com.testing.repository.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "ClientApiClientMapper")
public interface ClientApiClientMapper {
    ClientApi clientDtoToClientApi(Client source);
    Client clientApiToClientDto(ClientApi source);
}

