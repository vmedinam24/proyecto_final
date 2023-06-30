package com.nttdata.bankproducts.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
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
