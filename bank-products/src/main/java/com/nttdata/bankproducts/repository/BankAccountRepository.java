package com.nttdata.bankproducts.repository;

import com.nttdata.bankproducts.document.BankAccount;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository
        extends RxJava3CrudRepository<BankAccount, String>  {
    Maybe<BankAccount> findByclientId(String clientId);

}
