package com.ejemplo.demopaginaweb.controller;


import com.ejemplo.demopaginaweb.entity.Producto;
import com.ejemplo.demopaginaweb.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("producto")
public class ProductoController {

    private final IProductoService iProductoService;

    @Autowired
    public ProductoController(IProductoService iProductoService) {
        this.iProductoService = iProductoService;
    }

    @GetMapping("/productos")
    public String getProductos(Model model) {
        model.addAttribute("titulo", "Listado de Productos");
        model.addAttribute("productos", iProductoService.findAll());
        return "producto/productos";
    }

    @GetMapping(value ="/cargar-productos/{term}" ,produces = {"application/json"})
    public @ResponseBody List<Producto> buscarProductos(@PathVariable String term) {
        return iProductoService.buscarProductosPorNombre(term);
    }

}
