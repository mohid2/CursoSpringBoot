package com.ejemplo.demopaginaweb.controller;

import com.ejemplo.demopaginaweb.entity.Factura;
import com.ejemplo.demopaginaweb.entity.Role;
import com.ejemplo.demopaginaweb.entity.Usuario;
import com.ejemplo.demopaginaweb.service.IUsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@SessionAttributes("usuario")
public class UsuarioController {
    private final IUsuarioService iUsuarioService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioController(IUsuarioService iUsuarioService, PasswordEncoder passwordEncoder) {
        this.iUsuarioService = iUsuarioService;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("/perfil")
    public String getUser(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();

            Optional<Usuario> usuario = iUsuarioService.getUserByUsername(username);
            if (usuario.isPresent()) {

                model.addAttribute("usuario", usuario.get());
                return "user";
            }
        }
        // El usuario no ha iniciado sesión, redirige a la página de inicio de sesión
        return "redirect:/login";
    }
    @GetMapping("/registro")
    public String getForm(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            // El usuario ya ha iniciado sesión, redirígelo a otra página
            model.addAttribute("errorMessage","El usuario ya ha iniciado sesión");
            return "redirect:/listar";
        }
        model.addAttribute("usuario",new Usuario());
        return "register";
    }
    @PostMapping("/registro")
    public String register(@ModelAttribute("usuario") Usuario usuario, Model model) {

        try {
            Role role1 = new Role("ROLE_ADMIN");
            Role role2 = new Role("ROLE_USER");
            role1.setUsuario(usuario);
            role2.setUsuario(usuario);
            usuario.addRole(role1);
            usuario.addRole(role2);
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            System.out.println(usuario.getPassword());
            usuario.setActivo(true);
            iUsuarioService.createUser(usuario);
        } catch (Exception e) {
            model.addAttribute("errorMessage",e.getMessage());
            System.out.println(e.getMessage());
            return "register";
        }
        return "redirect:/login";
    }
    @GetMapping("/eliminar")
    public String eliminarFactura(@ModelAttribute("usuario") Usuario usuario, Model model, RedirectAttributes flash, HttpSession session){
            iUsuarioService.deleteUser(usuario);
        // Invalida la sesión actual
            SecurityContextHolder.clearContext();
            session.invalidate();

            flash.addFlashAttribute("success","Usuario borrado con exito");
             return "redirect:/login";
    }
}
