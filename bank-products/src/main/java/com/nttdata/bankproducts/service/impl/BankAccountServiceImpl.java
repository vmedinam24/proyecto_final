package com.nttdata.bankproducts.service.impl;

import com.nttdata.bankproducts.document.BankAccount;
import com.nttdata.bankproducts.document.Tranfers;
import com.nttdata.bankproducts.response.BankAccountByDebitCard;
import com.nttdata.bankproducts.response.BankAccountMovements;
import com.nttdata.bankproducts.response.BankAccountResponse;
import com.nttdata.bankproducts.response.ProductBankByClient;
import com.nttdata.bankproducts.feign.ClientFeign;
import com.nttdata.bankproducts.repository.BankAccountRepository;
import com.nttdata.bankproducts.repository.BankCreditsRepository;
import com.nttdata.bankproducts.service.BankAccountService;
import com.nttdata.bankproducts.util.BankAccountMapper;
import com.nttdata.bankproducts.util.BankAccountMovementMapper;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankCreditsRepository bankCreditsRepository;

    @Autowired
    private ClientFeign clientFeign;

    @Override
    public Flowable<BankAccountResponse> getAll() {
        return this.bankAccountRepository
                .findAll()
                .map(BankAccountMapper.INSTANCE::map);
    }
    @Override
    public Maybe<BankAccountResponse> create(BankAccount bankAccount) {
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
                .flatMapSingle(bankAccountRepository::save)
                .map(BankAccountMapper.INSTANCE::map);
    }

    @Override
    public Maybe<BankAccountResponse> read(String bankAccountId) {
        return bankAccountRepository
                .findById(bankAccountId)
                .map(BankAccountMapper.INSTANCE::map);
    }

    @Override
    public Maybe<BankAccountResponse> deposit(String bankAccountId, Double amount) {
        Tranfers tranfers = new Tranfers("deposit",
                DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a").format(LocalDateTime.now()),
                amount);
        return this.bankAccountRepository
                .findById(bankAccountId)
                .switchIfEmpty(Maybe.error(new NotFoundException(
                        "bankAccountId doesn't exist: " + bankAccountId)))
                .flatMap(bankAccount -> {
                    bankAccount
                            .setTotalAmount(
                                    bankAccount.getTotalAmount() + amount);
                    bankAccount
                            .setTotalTransfers(
                                    bankAccount.getTotalTransfers() + 1);
                    bankAccount.getTranfersList().add(tranfers);
                    if (bankAccount.getTotalTransfers() >= 20){
                        bankAccount.setCountCommissions(bankAccount.getCountCommissions() + 1);
                        bankAccount.setTotalAmount(
                                bankAccount.getTotalAmount() - 4);
                    }
                    return Maybe.just(bankAccount);
                })
                .flatMapSingle(this.bankAccountRepository::save)
                .map(BankAccountMapper.INSTANCE::map);

    }

    @Override
    public Maybe<BankAccountResponse> withdraw(String bankAccountId, Double amount) {
        Tranfers tranfers = new Tranfers("withdraw",
                DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a").format(LocalDateTime.now()),
                amount);
        return this.bankAccountRepository
                .findById(bankAccountId)
                .switchIfEmpty(Maybe.error(new NotFoundException(
                        "bankAccountId doesn't exist: " + bankAccountId)))
                .flatMap(bankAccount -> {
                    if (bankAccount.getTotalAmount() > amount) {
                        bankAccount.setTotalAmount(
                                bankAccount.getTotalAmount() - amount);
                        bankAccount
                                .setTotalTransfers(
                                        bankAccount.getTotalTransfers() + 1);
                        bankAccount.getTranfersList().add(tranfers);
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
                .flatMapSingle(this.bankAccountRepository::save)
                .map(BankAccountMapper.INSTANCE::map);
    }

    /*@Override
    public Flowable<BankAccount> readByClientId(String clientId) {
        return this.bankAccountRepository.findByclientId(clientId);
    }*/

    @Override
    public Maybe<BankAccountResponse> transfers(String bankAccountId1,
                                        String bankAccountId2, Double amount) {
        return this.bankAccountRepository
                .findById(bankAccountId1)
                .flatMap(bankAccount -> deposit(bankAccountId1, amount))
                .flatMap(
                        bankAccount -> this.bankAccountRepository
                                .findById(bankAccountId2)
                                .flatMap(bankAccount1 -> withdraw(bankAccountId2, amount))
                );
    }

    @Override
    public ProductBankByClient summary(String clientId) {
        ProductBankByClient productBankByClient = new ProductBankByClient();
        productBankByClient.setClientId(clientId);
        productBankByClient.setBankAccounts(this.bankAccountRepository
                .findByclientId(clientId)
                .toStream()
                .collect(Collectors.toList()));
        productBankByClient.setBankCredits(this.bankCreditsRepository
                .findByclientId(clientId)
                .toStream()
                .collect(Collectors.toList()));
        return productBankByClient;
    }

    @Override
    public BankAccountByDebitCard reportByDebitCard(String numberDebitCard) {
        BankAccountByDebitCard bankAccountByDebitCard = new BankAccountByDebitCard();
        bankAccountByDebitCard.setNumberDebitCard(numberDebitCard);
        bankAccountByDebitCard.setBankAccounts(this.bankAccountRepository
                .findBynumberDebitCard(numberDebitCard)
                .toStream()
                .collect(Collectors.toList()));
        return bankAccountByDebitCard;
    }

    @Override
    public Maybe<BankAccountMovements> movements(String bankAccountId) {
        return this.bankAccountRepository.findById(bankAccountId)
                .map(BankAccountMovementMapper.INSTANCE::map);
    }

}
