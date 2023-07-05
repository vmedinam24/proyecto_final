package com.nttdata.bankproducts.document;

import java.util.ArrayList;
import java.util.List;
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
@Document(collection = "accounts")
public class BankAccount {
  @Id
  private String accountNumberId;
  private String clientId;
  private String numberDebitCard;
  private String nameTypeAccount;
  private Double totalAmount;
  private Integer totalTransfers = 0;
  private Integer countCommissions = 0;
  private List<Tranfers> tranfersList = new ArrayList<>();
}
