package com.ejemplo.demopaginaweb.service;


import com.ejemplo.demopaginaweb.entity.Producto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductoService {
    List<Producto> findAll();

    Page<Producto> fidAll();

    void save(Producto producto);

    void deleteById(Long id);

    Producto findById(Long id);

    List<Producto> buscarProductosPorNombre(String term);
}
