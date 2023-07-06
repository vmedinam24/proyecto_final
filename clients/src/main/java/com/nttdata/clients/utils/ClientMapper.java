package com.nttdata.clients.utils;

import com.nttdata.clients.document.Client;
import com.nttdata.clients.dto.ClientDto;

public interface ClientMapper {

  //ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

  /**
   * Maps a data type client dto to client.
   *
   * @param clientDto   an object to convert.
   * @return  client    with data of client dto.
  */
  default Client map(ClientDto clientDto) {
    return Client.builder()
        .clientId(clientDto.getClientId())
        .name(clientDto.getName())
        .lastName(clientDto.getLastName())
        .phone(clientDto.getPhone())
        .email(clientDto.getEmail())
        .type(clientDto.getType())
        .build();
  }

  /**
   * Maps a data type client to client dto.
   *
   * @param client   an object to convert.
   * @return  clientDto    with data of client.
   */
  default ClientDto map(Client client) {
    return ClientDto.builder()
        .clientId(client.getClientId())
        .name(client.getName())
        .lastName(client.getLastName())
        .phone(client.getPhone())
        .email(client.getEmail())
        .type(client.getType())
        .build();
  }
}
