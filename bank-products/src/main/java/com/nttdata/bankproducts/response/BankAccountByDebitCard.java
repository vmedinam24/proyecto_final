package com.nttdata.bankproducts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nttdata.bankproducts.document.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankAccountByDebitCard {

    private String numberDebitCard;
    private List<BankAccountResponse> bankAccounts;

}
