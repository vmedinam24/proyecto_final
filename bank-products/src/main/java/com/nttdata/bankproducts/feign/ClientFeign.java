package com.nttdata.bankproducts.feign;

import com.nttdata.bankproducts.document.Client;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "micro-client")
public interface ClientFeign {

    @GetMapping("/get/{clientId}")
    Maybe<Client> read(@PathVariable String clientId);

}
