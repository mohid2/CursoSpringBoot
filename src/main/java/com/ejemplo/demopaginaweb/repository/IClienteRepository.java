package com.ejemplo.demopaginaweb.repository;

import com.ejemplo.demopaginaweb.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


public interface IClienteRepository extends JpaRepository<Cliente, Long> {

	@Query("select c from Cliente c left join fetch c.facturas f where c.id=?1")
	public Optional<Cliente> fetchByIdWithFacturas(Long id);
}
