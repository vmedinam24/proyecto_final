package com.nttdata.clients.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDto {
    private String clientId;
    private String name;
    private String lastName;
    private Integer phone;
    private String email;
    private String type;
}
