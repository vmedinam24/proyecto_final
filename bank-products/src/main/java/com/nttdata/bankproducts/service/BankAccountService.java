package com.nttdata.bankproducts.service;

import com.nttdata.bankproducts.document.BankAccount;
import com.nttdata.bankproducts.response.BankAccountByDebitCard;
import com.nttdata.bankproducts.response.BankAccountMovements;
import com.nttdata.bankproducts.response.BankAccountResponse;
import com.nttdata.bankproducts.response.ProductBankByClient;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

public interface BankAccountService {

  Flowable<BankAccountResponse> getAll();

  Maybe<BankAccountResponse> create(BankAccount bankAccount);

  Maybe<BankAccountResponse> read(String bankAccountId);

  Maybe<BankAccountResponse> deposit(String bankAccountId, Double amount);

  Maybe<BankAccountResponse> withdraw(String bankAccountId, Double amount);
  //Flowable<BankAccount> readByClientId(String clientId);

  Maybe<BankAccountResponse> transfers(String bankAccountId1, String bankAccountId2, Double amount);

  /********************************************************************/
  ProductBankByClient summary(String clientId);

  BankAccountByDebitCard reportByDebitCard(String numberDebitCard);

  Maybe<BankAccountMovements> movements(String bankAccountId);
}
