package com.nttdata.bankproducts.document;

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
@Document(collection = "credits")
public class BankCredits {

    @Id
    private String creditsId;
    private String clientId;
    private String nameTypeCredits;
    private Double creditsAmount;
    private Double totalAmount;
}
