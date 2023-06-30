package com.nttdata.bankproducts.service.impl;

import com.nttdata.bankproducts.document.BankAccount;
import com.nttdata.bankproducts.feign.ClientFeign;
import com.nttdata.bankproducts.repository.BankAccountRepository;
import com.nttdata.bankproducts.service.BankAccountService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private ClientFeign clientFeign;

    @Override
    public Flowable<BankAccount> getAll() {
        return this.bankAccountRepository
                .findAll();
    }
    @Override
    public Maybe<BankAccount> create(BankAccount bankAccount) {
        return this.bankAccountRepository
                .findById(bankAccount.getAccountNumberId())
                .flatMap(
                        bankAccount1 -> Maybe.error(new Error("BankAccount exist")),
                        error -> Maybe.error(error),
                        () -> Maybe.just(bankAccount)
                )
                .flatMap(bankAccount1 -> {
                    if (bankAccount1.getNameTypeAccount()
                            .equalsIgnoreCase("ahorro")
                            || bankAccount1.getNameTypeAccount()
                            .equalsIgnoreCase("corriente")
                            || bankAccount1.getNameTypeAccount()
                            .equalsIgnoreCase("plazo fijo")) {
                        return Maybe.just(bankAccount1);
                    } else {
                        return Maybe.error(
                                new Error("Doesn't exist type of account"));
                    }
                })
                .flatMapSingle(bankAccountRepository::save);
    }

    @Override
    public Maybe<BankAccount> read(String bankAccountId) {
        return bankAccountRepository.findById(bankAccountId);
    }

    @Override
    public Maybe<BankAccount> deposit(String bankAccountId, Double amount) {
        return this.bankAccountRepository.findById(bankAccountId)
                .switchIfEmpty(Maybe.error(new NotFoundException(
                        "bankAccountId doesn't exist: " + bankAccountId)))
                .flatMap(bankAccount -> {
                    bankAccount
                            .setTotalAmount(
                                    bankAccount.getTotalAmount() + amount);
                    bankAccount
                            .setTotalTransfers(
                                    bankAccount.getTotalTransfers() + 1);
                    if (bankAccount.getTotalTransfers() >= 20){
                        bankAccount.setCountCommissions(bankAccount.getCountCommissions() + 1);
                        bankAccount.setTotalAmount(
                                bankAccount.getTotalAmount() - 4);
                    }
                    return Maybe.just(bankAccount);
                })
                .flatMapSingle(this.bankAccountRepository::save);

    }

    @Override
    public Maybe<BankAccount> withdraw(String bankAccountId, Double amount) {
        return this.bankAccountRepository.findById(bankAccountId)
                .switchIfEmpty(Maybe.error(new NotFoundException(
                        "bankAccountId doesn't exist: " + bankAccountId)))
                .flatMap(bankAccount -> {
                    if (bankAccount.getTotalAmount() > amount) {
                        bankAccount.setTotalAmount(
                                bankAccount.getTotalAmount() - amount);
                        bankAccount
                                .setTotalTransfers(
                                        bankAccount.getTotalTransfers() + 1);
                        if (bankAccount.getTotalTransfers() >= 20){
                            bankAccount.setCountCommissions(bankAccount.getCountCommissions() + 1);
                            bankAccount.setTotalAmount(
                                    bankAccount.getTotalAmount() - 4);
                        }
                        return Maybe.just(bankAccount);
                    } else {
                        return Maybe.error(new Error("Unable to withdraw"));
                    }
                })
                .flatMapSingle(this.bankAccountRepository::save);
    }

    @Override
    public Maybe<BankAccount> readByClientId(String clientId) {
        return this.bankAccountRepository.findByclientId(clientId);
    }

    @Override
    public Maybe<BankAccount> transfers(String bankAccountId1,
                                        String bankAccountId2, Double amount) {
        return this.bankAccountRepository.findById(bankAccountId1)
                .flatMap(bankAccount -> deposit(bankAccountId1, amount))
                .flatMap(
                        bankAccount -> this.bankAccountRepository.findById(bankAccountId2)
                                .flatMap(bankAccount1 -> withdraw(bankAccountId2, amount))
                                .flatMapSingle(this.bankAccountRepository::save)
                )
                .flatMapSingle(this.bankAccountRepository::save);
    }
}
