package com.nttdata.bankproducts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nttdata.bankproducts.document.Tranfers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankAccountMovements {

    private String accountNumberId;
    private String clientId;
    private String numberDebitCard;
    private String nameTypeAccount;
    private Double totalAmount;
    private List<Tranfers> tranfersList = new ArrayList<>();

}
