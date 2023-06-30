package com.nttdata.clients.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "clients")
public class Client {

    @Id
    private String clientId;
    private String name;
    private String lastName;
    private Integer phone;
    private String email;
    private String type;
}
