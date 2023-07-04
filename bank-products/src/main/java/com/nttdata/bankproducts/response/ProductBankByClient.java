package com.nttdata.bankproducts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nttdata.bankproducts.document.BankAccount;
import com.nttdata.bankproducts.document.BankCredits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductBankByClient {

    private String clientId;
    private List<BankAccountResponse> bankAccounts;
    private List<BankCredits> bankCredits;

}
