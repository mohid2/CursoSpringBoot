package com.ejemplo.demopaginaweb.repository;

import com.ejemplo.demopaginaweb.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface IFacturaRepository extends JpaRepository<Factura, Long> {

	@Query("select f from Factura f join fetch f.cliente c join fetch f.itemFacturas l join fetch l.producto where f.id=?1")
	public Optional<Factura> fetchByIdWithClienteWithItemFacturaWithProducto(Long id);
}
