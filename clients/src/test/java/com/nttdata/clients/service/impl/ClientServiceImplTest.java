package com.nttdata.clients.service.impl;

import com.nttdata.clients.document.Client;
import com.nttdata.clients.repository.ClientRepository;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Collectors;

import static com.nttdata.clients.util.TestUtil.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

  @InjectMocks
  ClientServiceImpl clientServiceImpl;

  @Mock
  ClientRepository clientRepository;

  @Test
  void getAll(){
    when(clientRepository.findAll()).thenReturn(listClient());
    Flowable<Client> clientFlowable = clientServiceImpl.getAll();

    assertEquals(2, clientFlowable.blockingStream().collect(Collectors.toList()).size());
  }

  @Test
  void create(){
    //Single<Client> single = Single.just(createClient());
    //when(clientRepository.save(any(Client.class))).thenReturn(single);
    //Maybe<Client> clientMaybe = clientServiceImpl.create(createClient());

    //assertEquals("Medina", clientMaybe.blockingGet().getLastName());

  }

  @Test
  void read(){
    when(clientRepository.findById(Mockito.anyString())).thenReturn(createClientOther());
    Maybe<Client> clientMaybe = clientServiceImpl.read(createClient().getClientId());

    assertEquals("4587963215", clientMaybe.blockingGet().getClientId());
  }

  @Test
  void update(){

  }

  @Test
  void delete(){
    when(clientRepository.deleteById(Mockito.anyString())).thenReturn(null);
    Completable completable = clientServiceImpl.delete("1458726");

    assertEquals(null,completable);
  }

}
