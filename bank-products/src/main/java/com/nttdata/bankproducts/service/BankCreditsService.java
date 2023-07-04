package com.nttdata.bankproducts.service;

import com.nttdata.bankproducts.document.BankAccount;
import com.nttdata.bankproducts.document.BankCredits;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

public interface BankCreditsService {

    Maybe<BankCredits> create(BankCredits bankCredits);
    Maybe<BankCredits> read(String bankCreditsId);
    Maybe<BankCredits> productPayment(String bankCreditsId, Double amount);
    Maybe<BankCredits> productCharge(String bankCreditsId, Double amount);
    //Flowable<BankCredits> reportByCredits(String clientId);
}
