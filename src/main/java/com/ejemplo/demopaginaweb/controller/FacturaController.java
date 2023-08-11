package com.ejemplo.demopaginaweb.controller;


import com.ejemplo.demopaginaweb.entity.Cliente;
import com.ejemplo.demopaginaweb.entity.Factura;
import com.ejemplo.demopaginaweb.entity.ItemFactura;
import com.ejemplo.demopaginaweb.entity.Producto;
import com.ejemplo.demopaginaweb.service.IClienteService;
import com.ejemplo.demopaginaweb.service.IFacturaService;
import com.ejemplo.demopaginaweb.service.IProductoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("factura")
@RequestMapping("factura")
public class FacturaController {
    private IFacturaService iFacturaService;
    private IClienteService iClienteService;
    private IProductoService iProductoService;
    private static final Logger logger = LoggerFactory.getLogger(Factura.class);

    @Autowired
    public FacturaController(IFacturaService iFacturaService, IClienteService iClienteService, IProductoService iProductoService) {
        this.iFacturaService = iFacturaService;
        this.iClienteService = iClienteService;
        this.iProductoService = iProductoService;
    }

    @GetMapping("/factura/{id}")
    public String getFactura(@PathVariable Long id, Model model,RedirectAttributes flash){
        model.addAttribute("titulo","Detalles de Factura");
        Optional<Factura> factura= iFacturaService.fetchByIdWithClienteWithItemFacturaWithProducto(id);   //iFacturaService.findById(id);
        if (factura.isEmpty()){
            flash.addFlashAttribute("error","La factura con el id "+id+" no existe");
            return "redirect:/listar";
        }
        model.addAttribute("factura",factura.get());
        return "factura/factura";
    }
    @GetMapping("/form/{clienteId}")
    public String crearFactura(@PathVariable Long clienteId , Model model, RedirectAttributes flash,SessionStatus status){
        Cliente cliente=iClienteService.findById(clienteId);
        if(cliente==null){
            flash.addFlashAttribute("error","El cliente no existe");
            return "redirect:/listar";
        }
        Factura factura=new Factura();
        factura.setCliente(cliente);
        model.addAttribute("factura",factura);
        model.addAttribute("titulo","Crear Nueva Factura");
        return "factura/form";
    }
   @PostMapping("/form")
    public String guardar(@Valid Factura factura,BindingResult result,Model model,
                          @RequestParam(name ="item_id[]",required = false) Long[] itemId,
                          @RequestParam(name = "cantidad[]",required = false) Integer[] cantidad,
                          RedirectAttributes flash, SessionStatus status){
        if(result.hasErrors()){
            model.addAttribute("titulo","Crear Nueva Factura");
            return "factura/form";
        }
        if(itemId==null || itemId.length==0){
            model.addAttribute("titulo", "Crear Factura");
            model.addAttribute("error", "Error: La factura NO puede no tener líneas!");
            return "factura/form";
        }
       for(int i=0; i<itemId.length; i++){
            Producto producto=iProductoService.findById(itemId[i]);
            ItemFactura itemFactura=new ItemFactura();
           itemFactura.setCantidad(cantidad[i]);
           itemFactura.setProducto(producto);
           factura.addItems(itemFactura);
       }
        iFacturaService.save(factura);
        flash.addFlashAttribute("success","La factura se ha creado correctamente");
        status.setComplete();
        return "redirect:/ver/"+factura.getCliente().getId();
    }
    @GetMapping("/eliminar/{id}")
    public String eliminarFactura(@PathVariable Long id , Model model, RedirectAttributes flash){
        Optional<Factura> factura =iFacturaService.findById(id);
        if(factura.isPresent()){
            iFacturaService.deleteById(id);
            flash.addFlashAttribute("success","La factura ha sido eliminada ");
            return "redirect:/ver/"+factura.get().getCliente().getId();
        }
        flash.addFlashAttribute("error","La factura no existe");
        return "redirect:/listar";
    }

}
