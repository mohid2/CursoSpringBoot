package com.ejemplo.demopaginaweb.service.impl;


import com.ejemplo.demopaginaweb.entity.Producto;
import com.ejemplo.demopaginaweb.repository.IProductoRepository;
import com.ejemplo.demopaginaweb.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ProductoServiceImpl implements IProductoService {

    private final IProductoRepository iProductoRepository;
    @Autowired
    public ProductoServiceImpl(IProductoRepository iProductoRepository) {

        this.iProductoRepository = iProductoRepository;
    }

    @Override
    public List<Producto> findAll() {
        return iProductoRepository.findAll();
    }

    @Override
    public Page<Producto> fidAll() {
        return null;
    }

    @Override
    public void save(Producto producto) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    @Transactional(readOnly = true)
    public Producto findById(Long id) {
        return iProductoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Producto> buscarProductosPorNombre(String term) {
        return iProductoRepository.findByNombre(term);
    }
}
