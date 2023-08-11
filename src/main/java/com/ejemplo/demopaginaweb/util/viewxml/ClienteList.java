package com.ejemplo.demopaginaweb.util.viewxml;

import com.ejemplo.demopaginaweb.entity.Cliente;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


import java.util.List;

@XmlRootElement(name = "clientes")
public class ClienteList {
    @XmlElement(name = "cliente")
    public List<Cliente> clientes;

    public ClienteList() {
    }

    public ClienteList(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}
