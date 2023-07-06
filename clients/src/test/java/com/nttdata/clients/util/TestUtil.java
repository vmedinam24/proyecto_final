package com.nttdata.clients.util;

import com.nttdata.clients.document.Client;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

public class TestUtil {

  public static Flowable<Client> listClient(){
    Client client = Client.builder()
        .clientId("4587963215")
        .name("Vivian")
        .lastName("Medina")
        .email("vmedinam@gmail.com")
        .phone(914905543)
        .type("personal")
        .build();

    Flowable<Client> clientFlowable = Flowable.just(client,client);

    return clientFlowable;
  }

  public static Client createClient(){
    Client client = Client.builder()
        .clientId("4587963215")
        .name("Vivian")
        .lastName("Medina")
        .email("vmedinam@gmail.com")
        .phone(914905543)
        .type("personal")
        .build();
    return client;
  }

  public static Maybe<Client> createClientOther(){
    Client client = Client.builder()
        .clientId("4587963215")
        .name("Vivian")
        .lastName("Medina")
        .email("vmedinam@gmail.com")
        .phone(914905543)
        .type("personal")
        .build();
    Maybe<Client> maybe = Maybe.just(client);
    return maybe;
  }

}
