package com.nttdata.clients.service.impl;

import com.nttdata.clients.document.Client;
import com.nttdata.clients.dto.ClientDto;
import com.nttdata.clients.repository.ClientRepository;
import com.nttdata.clients.service.ClientService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Flowable<Client> getAll() {
        return this.clientRepository.findAll();
    }

    @Override
    public Maybe<Client> create(Client client) {
        return this.clientRepository.findById(client.getClientId())
                .flatMap(
                        cliente1 -> Maybe.error(new Throwable("Client exist")),
                        (error) -> Maybe.error(error),
                        () -> Maybe.just(client)
                )
                .flatMap(client1 -> {
                    if (client1.getType().equalsIgnoreCase("personal")) {
                        return Maybe.just(client);
                    }
                    if (client1.getType()
                            .equalsIgnoreCase("empresarial")) {
                        return Maybe.just(client);
                    } else {
                        return Maybe.error(new Error(
                                "Doesn't exist this type"));
                    }
                })
                .flatMapSingle(clientRepository::save);

    }

    @Override
    public Maybe<Client> read(String clientId) {
        return this.clientRepository.findById(clientId);
    }

    @Override
    public Maybe<Client> update(Client client) {
        return this.clientRepository.findById(client.getClientId())
                .switchIfEmpty(Maybe.error(new NotFoundException(
                        "Non existent clientId: " + client.getClientId())))
                .flatMap(cliente1 -> Maybe.just(client))
                .flatMapSingle(clientRepository::save);
    }

    @Override
    public Completable delete(String clientId) {
       return this.clientRepository.deleteById(clientId);
    }

}
