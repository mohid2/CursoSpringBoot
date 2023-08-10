package com.ejemplo.demopaginaweb.service;


import com.ejemplo.demopaginaweb.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IClienteService {
    List<Cliente> findAll();
    Page<Cliente> findAll(Pageable pageable);


    void save(Cliente cliente);


    Cliente findById(Long id);

    void deleteById(Long id);
    Optional<Cliente> fetchByIdWithFacturas(Long id);

}
