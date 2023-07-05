package com.nttdata.bankproducts.repository;

import com.nttdata.bankproducts.document.BankAccount;
import com.nttdata.bankproducts.response.BankAccountResponse;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BankAccountRepository
    extends RxJava3CrudRepository<BankAccount, String>  {
  Flux<BankAccountResponse> findByclientId(String clientId);

  Flux<BankAccountResponse> findBynumberDebitCard(String numberDebitCard);
}
