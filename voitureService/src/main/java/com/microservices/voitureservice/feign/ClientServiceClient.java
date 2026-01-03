package com.microservices.voitureservice.feign;

import com.microservices.voitureservice.model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client-service")
public interface ClientServiceClient {
    @GetMapping("/api/clients/{id}")
    Client getClientById(@PathVariable Long id);
}

