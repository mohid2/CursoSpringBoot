package com.ejemplo.demopaginaweb.service;



import com.ejemplo.demopaginaweb.entity.Factura;

import java.util.List;
import java.util.Optional;

public interface IFacturaService {
    List<Factura> findAll();

    void save(Factura factura);
    void deleteById(Long id);
    Optional<Factura> findById(Long id);
    Optional<Factura> fetchByIdWithClienteWithItemFacturaWithProducto(Long id);
}
