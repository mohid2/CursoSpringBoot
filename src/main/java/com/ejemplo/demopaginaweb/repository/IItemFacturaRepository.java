package com.ejemplo.demopaginaweb.repository;

import com.ejemplo.demopaginaweb.entity.ItemFactura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemFacturaRepository extends JpaRepository<ItemFactura,Long> {
}
