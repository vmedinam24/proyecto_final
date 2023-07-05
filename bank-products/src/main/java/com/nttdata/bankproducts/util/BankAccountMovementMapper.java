package com.nttdata.bankproducts.util;

import com.nttdata.bankproducts.document.BankAccount;
import com.nttdata.bankproducts.response.BankAccountMovements;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BankAccountMovementMapper {

  BankAccountMovementMapper INSTANCE = Mappers.getMapper(BankAccountMovementMapper.class);

  default BankAccount map(BankAccountMovements bankAccountMovements) {
    return BankAccount.builder()
        .accountNumberId(bankAccountMovements.getAccountNumberId())
        .clientId(bankAccountMovements.getClientId())
        .numberDebitCard(bankAccountMovements.getNumberDebitCard())
        .nameTypeAccount(bankAccountMovements.getNameTypeAccount())
        .totalAmount(bankAccountMovements.getTotalAmount())
        .tranfersList(bankAccountMovements.getTranfersList())
        .build();
  }

  default BankAccountMovements map(BankAccount bankAccount) {
    return BankAccountMovements.builder()
        .accountNumberId(bankAccount.getAccountNumberId())
        .clientId(bankAccount.getClientId())
        .numberDebitCard(bankAccount.getNumberDebitCard())
        .nameTypeAccount(bankAccount.getNameTypeAccount())
        .totalAmount(bankAccount.getTotalAmount())
        .tranfersList(bankAccount.getTranfersList())
        .build();
  }

}
