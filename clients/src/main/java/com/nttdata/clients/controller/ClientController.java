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

  @RequestMapping
  public Flowable<Client> getAll() {
    return this.clientService.getAll();
  }

  @PostMapping
  public Maybe<Client> create(@RequestBody Client client) {
    return clientService.create(client);
  }

  @GetMapping("/get/{clientId}")
  public Maybe<Client> read(@PathVariable String clientId) {
    return clientService.read(clientId);
  }

  /**
   * Metodo update, modifica los atributos de un objeto cliente.
   *
   * @param client objeto de tipo cliente
   * @return regresa un Maybe con los datos actualizados
   */
  @PutMapping("/update")
  public Maybe<Client> update(@RequestBody Client client) {
    return clientService.update(client);
  }

  /**
   * Metodo delete, borra de manera permanente un objeto de tipo cliente.
   *
   * @param clientId id del cliente
   * @return regresa un Completable correcto
   */
  @DeleteMapping("/delete/{clientId}")
  public Completable delete(@PathVariable String clientId) {
    return clientService.delete(clientId);
  }


}
