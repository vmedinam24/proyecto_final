package com.nttdata.bankproducts.document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
@JsonSerialize
@JsonDeserialize
@Document(collection = "accounts")
public class BankAccount {
    @Id
    private String accountNumberId;
    private String clientId;
    private String nameTypeAccount;
    private Double totalAmount;
    private Integer totalTransfers = 0;
    private Integer countCommissions = 0;
}
