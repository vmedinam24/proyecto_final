package com.nttdata.bankproducts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankAccountByDebitCard {
  private String numberDebitCard;
  private List<BankAccountResponse> bankAccounts;
}
