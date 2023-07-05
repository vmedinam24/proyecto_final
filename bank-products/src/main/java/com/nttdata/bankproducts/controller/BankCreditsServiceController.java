package com.nttdata.bankproducts.controller;

import com.nttdata.bankproducts.document.BankCredits;
import com.nttdata.bankproducts.service.BankCreditsService;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/credits")
public class BankCreditsServiceController {

  @Autowired
  private BankCreditsService bankCreditsService;

  @PostMapping("/credits")
  public Maybe<BankCredits> create(@RequestBody BankCredits bankCredits) {
    return bankCreditsService.create(bankCredits);
  }

  @GetMapping("/credits/get/{bankCreditsId}")
  public Maybe<BankCredits> read(@PathVariable String bankCreditsId) {
    return this.bankCreditsService.read(bankCreditsId);
  }

  @PutMapping("/credits/productPayment")
  Maybe<BankCredits> productPayment(@RequestParam String bankCreditsId,
                                    @RequestParam Double amount) {
    return this.bankCreditsService.productPayment(bankCreditsId, amount);
  }

  @PutMapping("/credits/productCharge")
  Maybe<BankCredits> productCharge(@RequestParam String bankCreditsId,
                                   @RequestParam Double amount) {
    return this.bankCreditsService.productCharge(bankCreditsId, amount);
  }

  /*@GetMapping("/report")
    public Flowable<BankCredits> reportByCredits(@RequestParam String clientId){
        return this.bankCreditsService.reportByCredits(clientId);
    }*/


}
