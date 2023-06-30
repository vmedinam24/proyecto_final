package com.nttdata.clients.utils;

import com.nttdata.clients.document.Client;
import com.nttdata.clients.dto.ClientDto;

public class ClientConverter {

    public static ClientDto clientToDto(Client client) {
        ClientDto dto = new ClientDto();
        dto.setClientId(client.getClientId());
        dto.setName(client.getName());
        dto.setLastName(client.getLastName());
        dto.setPhone(client.getPhone());
        dto.setEmail(client.getEmail());
        dto.setType(client.getType());

        return dto;
    }

    public static Client dtoToClient(ClientDto clientDto) {
        Client client = new Client();
        client.setClientId(clientDto.getClientId());
        client.setName(clientDto.getName());
        client.setLastName(clientDto.getLastName());
        client.setPhone(clientDto.getPhone());
        client.setEmail(clientDto.getEmail());
        client.setType(clientDto.getType());

        return client;
    }
}
