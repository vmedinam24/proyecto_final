package com.nttdata.bankproducts.service;

import com.nttdata.bankproducts.document.BankAccount;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

public interface BankAccountService {

    Flowable<BankAccount> getAll();
    Maybe<BankAccount> create(BankAccount bankAccount);
    Maybe<BankAccount> read(String bankAccountId);
    Maybe<BankAccount> deposit(String bankAccountId, Double amount);
    Maybe<BankAccount> withdraw(String bankAccountId, Double amount);
    Maybe<BankAccount> readByClientId(String clientId);
    Maybe<BankAccount> transfers(String bankAccountId1, String bankAccountId2, Double amount);





}
