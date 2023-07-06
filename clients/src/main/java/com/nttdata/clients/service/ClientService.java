package com.nttdata.clients.service;

import com.nttdata.clients.document.Client;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;


public interface ClientService {

  /**
   * Returns all the customers in the database
   * in the Flowable data type.
   *
   * @return Flowable
   */
  Flowable<Client> getAll();

  /**
   * Create a new client.
   *
   * @param client    an object of client.
   * @return Maybe
   */
  Maybe<Client> create(Client client);

  /**
   * Displays the required client based on its identifier.
   *
   * @param clientId    a string of client identifier.
   * @return  Maybe
   */
  Maybe<Client> read(String clientId);

  /**
   * Update a client with new data.
   *
   * @param client    an object of client.
   * @return Maybe
   */
  Maybe<Client> update(Client client);

  /**
   * Delete a client based on its identifier.
   *
   * @param clientId    a string of client identifier.
   * @return Completable
   */
  Completable delete(String clientId);

}
