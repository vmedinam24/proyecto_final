package com.nttdata.bankproducts.repository;

import com.nttdata.bankproducts.document.BankCredits;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankCreditsRepository
        extends RxJava3CrudRepository<BankCredits, String> {

    Flowable<BankCredits> findByclientId(String clientId);
}
