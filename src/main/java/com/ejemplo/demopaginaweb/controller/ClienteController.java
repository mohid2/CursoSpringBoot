package com.ejemplo.demopaginaweb.controller;

import com.ejemplo.demopaginaweb.entity.Cliente;
import com.ejemplo.demopaginaweb.service.IClienteService;
import com.ejemplo.demopaginaweb.service.IUploadFileService;
import com.ejemplo.demopaginaweb.util.paginator.PageRender;
import com.ejemplo.demopaginaweb.util.viewxml.ClienteList;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

    private IClienteService iClienteService;
    private IUploadFileService iUploadFileService;

    @Autowired
    public ClienteController(IClienteService iClienteService, IUploadFileService iUploadFileService) {
        this.iClienteService = iClienteService;
        this.iUploadFileService = iUploadFileService;
    }
    @GetMapping("/listar-rest")
    public @ResponseBody ClienteList listarRest(Model model) {

        return  new ClienteList( iClienteService.findAll());
    }

    @GetMapping(value = {"/listar","/"})
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 5);
        Page<Cliente> clientes = iClienteService.findAll(pageRequest);
        PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);
        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("clientes", clientes);
        model.addAttribute("page", pageRender);
        return "cliente/listar";
    }

    @GetMapping("/ver/{id}")
    public String detalles(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Optional<Cliente> cliente = iClienteService.fetchByIdWithFacturas(id);// iClienteService.findById(id);
        if (cliente.isEmpty()) {
            flash.addFlashAttribute("error", "El cliente no existe");
            return "redirect:/listar";
        }
        model.addAttribute("cliente", cliente.get());
        model.addAttribute("titulo", "Detalles  cliente ");
        return "cliente/ver";

    }

    @GetMapping("/form")
    public String vistaCrear(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        model.addAttribute("titulo", "Formulario de cliente");
        return "cliente/form";
    }

    @GetMapping("/form/{id}")
    public String vistaEditar(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Cliente cliente = null;
        if (id > 0) {
            cliente = iClienteService.findById(id);
            if (cliente == null) {
                flash.addFlashAttribute("error", "El cliente no existe");
                return "redirect:/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El cliente no existe");
            return "redirect:/listar";
        }
        model.addAttribute("cliente", cliente);
        model.addAttribute("titulo", "Editar cliente");
        return "cliente/form";
    }

    @PostMapping("/form")
    public String crear(@Valid Cliente cliente, BindingResult result, Model model,
            @RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de cliente");
            return "cliente/form";
        }
        if (!foto.isEmpty()) {
            if (cliente.getId() != null && cliente.getFoto() != null && cliente.getFoto().length() > 0) {
                iUploadFileService.delete(cliente.getFoto());
            }
            String uniqueFileName = null;
            try {
                uniqueFileName = iUploadFileService.copy(foto);
            } catch (IOException e) {
                e.getMessage();
            }
            flash.addFlashAttribute("info", "Has subido correctamente " + foto.getOriginalFilename());
            cliente.setFoto(uniqueFileName);
        }
        String mensajeFlash = (cliente.getId() != null) ? "Cliente editado con éxito" : "Cliente creado con éxito";
        iClienteService.save(cliente);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, Model mode, RedirectAttributes flash) {
        if (id > 0) {
            Cliente cliente = iClienteService.findById(id);

            if (iUploadFileService.delete(cliente.getFoto())) {
                flash.addFlashAttribute("info", "La foto " + cliente.getFoto() + " elimanda con exito");
            }
            iClienteService.deleteById(cliente.getId());
            flash.addFlashAttribute("success", "Cliente eliminado con éxito");
        }
        return "redirect:/listar";
    }

    @GetMapping(value = "/uploads/{filename:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
        Resource resource = null;
        try {
            resource = iUploadFileService.load(filename);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
                        resource.getFilename() + "\"")
                .body(resource);
    }
}
