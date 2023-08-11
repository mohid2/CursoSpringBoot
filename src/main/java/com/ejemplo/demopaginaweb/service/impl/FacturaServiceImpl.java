package com.ejemplo.demopaginaweb.service.impl;


import com.ejemplo.demopaginaweb.entity.Factura;
import com.ejemplo.demopaginaweb.repository.IFacturaRepository;
import com.ejemplo.demopaginaweb.service.IFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements IFacturaService {

    private final IFacturaRepository iFacturaRepository;

    @Autowired
    public FacturaServiceImpl(IFacturaRepository iFacturaRepository) {
        this.iFacturaRepository = iFacturaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Factura> findAll() {
        return iFacturaRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Factura factura) {
        iFacturaRepository.save(factura);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        iFacturaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Factura> findById(Long id) {
        return iFacturaRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Factura> fetchByIdWithClienteWithItemFacturaWithProducto(Long id) {
        return iFacturaRepository.fetchByIdWithClienteWithItemFacturaWithProducto(id);
    }
}
