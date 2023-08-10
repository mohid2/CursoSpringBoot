package com.ejemplo.demopaginaweb.service;



import com.ejemplo.demopaginaweb.entity.ItemFactura;

import java.util.List;

public interface IItemFacturaService {
    List<ItemFactura> findAll();
    void save(ItemFactura itemFactura);


    ItemFactura findById(Long id);

    void deleteById(Long id);
}
