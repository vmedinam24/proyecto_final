package com.nttdata.bankproducts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nttdata.bankproducts.document.BankCredits;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductBankByClient {

  private String clientId;
  private List<BankAccountResponse> bankAccounts;
  private List<BankCredits> bankCredits;

}
