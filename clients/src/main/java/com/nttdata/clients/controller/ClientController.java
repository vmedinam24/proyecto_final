package com.nttdata.clients.controller;

import com.nttdata.clients.document.Client;
import com.nttdata.clients.service.ClientService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/client")
public class ClientController {

  @Autowired
  private ClientService clientService;

  /**
   * Returns all the customers in the database.
   *
   * @return Flowable
   */
  @RequestMapping
  public Flowable<Client> getAll() {
    return this.clientService.getAll();
  }

  /**
   * Create a new client and check if the client exist.
   *
   * @param client    an object of client.
   * @return Maybe
   */
  @PostMapping
  public Maybe<Client> create(@RequestBody Client client) {
    return clientService.create(client);
  }

  /**
   * Displays the required client based on its identifier.
   *
   * @param clientId    a string of client identifier.
   * @return Maybe
   */
  @GetMapping("/get/{clientId}")
  public Maybe<Client> read(@PathVariable String clientId) {
    return clientService.read(clientId);
  }

  /**
   * Update a client with new data.
   *
   * @param client    an object of client.
   * @return Maybe
   */
  @PutMapping("/update")
  public Maybe<Client> update(@RequestBody Client client) {
    return clientService.update(client);
  }

  /**
   * Delete a client based on its identifier.
   *
   * @param clientId   a string of client identifier
   * @return Completable
   */
  @DeleteMapping("/delete/{clientId}")
  public Completable delete(@PathVariable String clientId) {
    return clientService.delete(clientId);
  }


}
