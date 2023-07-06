package com.nttdata.clients.controller;

import com.nttdata.clients.document.Client;
import com.nttdata.clients.service.ClientService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Collectors;

import static com.nttdata.clients.util.TestUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

  @InjectMocks
  ClientController clientController;

  @Mock
  ClientService clientService;

  @Test
  void getAll(){
    when(clientService.getAll()).thenReturn(listClient());
    Flowable<Client> clientFlowable = clientController.getAll();

    assertEquals(2, clientFlowable.blockingStream().collect(Collectors.toList()).size());
  }

  @Test
  void create(){
    when(clientService.create(Mockito.any(Client.class))).thenReturn(createClientOther());
    Maybe<Client> clientMaybe = clientController.create(createClient());

    assertEquals("Medina", clientMaybe.blockingGet().getLastName());
  }

  @Test
  void read(){
    when(clientService.read(Mockito.anyString())).thenReturn(createClientOther());
    Maybe<Client> maybe = clientController.read(createClient().getClientId());

    assertEquals("4587963215", maybe.blockingGet().getClientId());
  }

  @Test
  void update(){
    when(clientService.update(Mockito.any(Client.class))).thenReturn(createClientOther());
    Maybe<Client> clientMaybe = clientController.update(createClient());

    assertEquals("Vivian", clientMaybe.blockingGet().getName());
  }

  @Test
  void delete(){
    when(clientService.delete(Mockito.anyString())).thenReturn(null);
    Completable completable = clientController.delete("1458726");

    assertEquals(null,completable);
  }
}
