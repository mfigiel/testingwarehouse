package com.testing.api.controller;

import com.testing.api.resource.ClientApi;
import com.testing.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/clients")
    public List<ClientApi> getClients() {
        return clientService.getClients();
    }

    @PostMapping("/clients")
    void addClient(@RequestBody ClientApi client) {
        clientService.addClient(client);
    }

    @RequestMapping(value = "/client/{id}", method = RequestMethod.GET)
    public ClientApi getClientInformation(@PathVariable("id") long id) {
        return clientService.getClientInformation(id);
    }

}
