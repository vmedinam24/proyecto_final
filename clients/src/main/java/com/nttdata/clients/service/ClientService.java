package com.nttdata.clients.service;

import com.nttdata.clients.document.Client;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;


public interface ClientService {
        Flowable<Client> getAll();
        Maybe<Client> create(Client client);
        Maybe<Client> read(String clientId);
        Maybe<Client> update(Client client);
        Completable delete(String clientId);

}
