package com.ejemplo.demopaginaweb.service.impl;


import com.ejemplo.demopaginaweb.entity.Cliente;
import com.ejemplo.demopaginaweb.repository.IClienteRepository;
import com.ejemplo.demopaginaweb.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiveImpl implements IClienteService {
    private final IClienteRepository iClienteRepository;
    @Autowired
    public ClienteServiveImpl(IClienteRepository iClienteRepository) {
        this.iClienteRepository = iClienteRepository;
    }




    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return iClienteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAll(Pageable pageable) {
        return iClienteRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void save(Cliente cliente) {
        iClienteRepository.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(Long id) {
        return iClienteRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        iClienteRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> fetchByIdWithFacturas(Long id) {
        return iClienteRepository.fetchByIdWithFacturas(id);
    }
}
