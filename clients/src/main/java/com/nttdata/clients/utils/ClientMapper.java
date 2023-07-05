package com.nttdata.clients.utils;

import com.nttdata.clients.document.Client;
import com.nttdata.clients.dto.ClientDto;

public interface ClientMapper {

  //ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

  default Client map(ClientDto clientDto) {
    return Client.builder()
        .clientId(clientDto.getClientId())
        .name(clientDto.getName())
        .lastName(clientDto.getLastName())
        .phone(clientDto.getPhone())
        .email(clientDto.getEmail())
        .type(clientDto.getTipo())
        .build();
  }

  default ClientDto map(Client client) {
    return ClientDto.builder()
        .clientId(client.getClientId())
        .name(client.getName())
        .lastName(client.getLastName())
        .phone(client.getPhone())
        .email(client.getEmail())
        .tipo(client.getType())
        .build();
  }
}
