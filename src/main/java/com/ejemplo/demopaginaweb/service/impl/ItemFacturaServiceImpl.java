package com.ejemplo.demopaginaweb.service.impl;


import com.ejemplo.demopaginaweb.entity.ItemFactura;
import com.ejemplo.demopaginaweb.repository.IItemFacturaRepository;
import com.ejemplo.demopaginaweb.service.IItemFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemFacturaServiceImpl implements IItemFacturaService {
    private final IItemFacturaRepository iItemFacturaRepository;
    @Autowired
    public ItemFacturaServiceImpl(IItemFacturaRepository iItemFacturaRepository) {
        this.iItemFacturaRepository = iItemFacturaRepository;
    }

    @Override
    public List<ItemFactura> findAll() {
        return null;
    }

    @Override
    public void save(ItemFactura itemFactura) {

    }

    @Override
    public ItemFactura findById(Long id) {
        return iItemFacturaRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {

    }
}
