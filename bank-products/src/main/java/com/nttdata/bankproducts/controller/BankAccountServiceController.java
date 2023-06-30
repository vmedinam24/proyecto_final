package com.nttdata.bankproducts.controller;

import com.nttdata.bankproducts.document.BankAccount;
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
    public Flowable<BankAccount> getAll() {
        return bankAccountService.getAll();
    }

    @PostMapping("/account")
    public Maybe<BankAccount> create(@RequestBody BankAccount bankAccount) {
        return bankAccountService.create(bankAccount);
    }

    @GetMapping("/account/get/{bankAccountId}")
    public Maybe<BankAccount> read(@PathVariable String bankAccountId) {
        return this.bankAccountService.read(bankAccountId);
    }

    @GetMapping("/account/get/client/{clientId}")
    public Maybe<BankAccount> readByClientId(@PathVariable String clientId) {
        return this.bankAccountService.readByClientId(clientId);
    }

    @PutMapping("/account/deposit")
    public Maybe<BankAccount> deposit(@RequestParam String bankAccountId,
                                      @RequestParam Double amount) {
        return this.bankAccountService.deposit(bankAccountId, amount);
    }

    @PutMapping("/account/withdraw")
    public Maybe<BankAccount> withdraw(@RequestParam String bankAccountId,
                                       @RequestParam Double amount) {
        return this.bankAccountService.withdraw(bankAccountId, amount);
    }

    @PutMapping("/account/transfer")
    public Maybe<BankAccount> transfers(@RequestParam String accountToTransfer,
                                        @RequestParam String account,
                                        @RequestParam Double amount){
        return this.bankAccountService.transfers(accountToTransfer, account, amount);
    }


}
