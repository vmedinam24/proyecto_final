package com.nttdata.bankproducts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankAccountResponse {
  private String accountNumberId;
  private String clientId;
  private String numberDebitCard;
  private String nameTypeAccount;
  private Double totalAmount;
}
