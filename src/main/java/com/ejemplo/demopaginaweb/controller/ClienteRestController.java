package com.ejemplo.demopaginaweb.controller;

import com.ejemplo.demopaginaweb.service.IClienteService;
import com.ejemplo.demopaginaweb.util.viewxml.ClienteList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {
    private IClienteService iClienteService;

    @Autowired
    public ClienteRestController(IClienteService iClienteService) {
        this.iClienteService = iClienteService;

    }
    @GetMapping("/listar")
    public ClienteList listar(Model model) {
        return  new ClienteList( iClienteService.findAll());
    }

}
