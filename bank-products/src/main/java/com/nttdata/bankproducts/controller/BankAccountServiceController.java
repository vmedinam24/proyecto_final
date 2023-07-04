package com.nttdata.bankproducts.controller;

import com.nttdata.bankproducts.document.BankAccount;
import com.nttdata.bankproducts.response.BankAccountByDebitCard;
import com.nttdata.bankproducts.response.BankAccountMovements;
import com.nttdata.bankproducts.response.ProductBankByClient;
import com.nttdata.bankproducts.service.BankAccountService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/account")
public class BankAccountServiceController {

    @Autowired
    private BankAccountService bankAccountService;

    @RequestMapping("/account")
    public Flowable<?> getAll() {
        return bankAccountService.getAll();
    }

    @PostMapping("/account")
    public Maybe<?> create(@RequestBody BankAccount bankAccount) {
        return bankAccountService.create(bankAccount);
    }

    @GetMapping("/account/get/{bankAccountId}")
    public Maybe<?> read(@PathVariable String bankAccountId) {
        return this.bankAccountService.read(bankAccountId);
    }

    /*@GetMapping("/account/get/client/{clientId}")
    public Flowable<BankAccount> readByClientId(@PathVariable String clientId) {
        return this.bankAccountService.readByClientId(clientId);
    }*/

    @PutMapping("/account/deposit")
    public Maybe<?> deposit(@RequestParam String bankAccountId,
                                              @RequestParam Double amount) {
        return this.bankAccountService.deposit(bankAccountId, amount);
    }

    @PutMapping("/account/withdraw")
    public Maybe<?> withdraw(@RequestParam String bankAccountId,
                                       @RequestParam Double amount) {
        return this.bankAccountService.withdraw(bankAccountId, amount);
    }

    @PutMapping("/account/transfer")
    public Maybe<?> transfers(@RequestParam String accountToTransfer,
                                        @RequestParam String account,
                                        @RequestParam Double amount){
        return this.bankAccountService.transfers(accountToTransfer, account, amount);
    }

    @GetMapping("/summary")
    public ProductBankByClient summary(@RequestParam String clientId){
        return this.bankAccountService.summary(clientId);
    }

    @GetMapping("/debitcard/report")
    public BankAccountByDebitCard reportByDebitCard(@RequestParam String numberDebitCard){
        return this.bankAccountService.reportByDebitCard(numberDebitCard);
    }

    @GetMapping("/movements")
    public Maybe<BankAccountMovements> movements(@RequestParam String bankAccountId){
        return this.bankAccountService.movements(bankAccountId);
    }
}
