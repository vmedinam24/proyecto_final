package com.nttdata.clients.repository;

import com.nttdata.clients.document.Client;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository
        extends RxJava3CrudRepository<Client, String> {
}
