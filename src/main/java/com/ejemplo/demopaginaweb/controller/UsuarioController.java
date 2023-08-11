package com.ejemplo.demopaginaweb.controller;

import com.ejemplo.demopaginaweb.entity.Role;
import com.ejemplo.demopaginaweb.entity.Usuario;
import com.ejemplo.demopaginaweb.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

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

    @GetMapping("/registro")
    public String getForm(Model model){
        model.addAttribute("titulo","registro");
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
            System.out.println(usuario);
            iUsuarioService.createUser(usuario);
        } catch (Exception e) {
            model.addAttribute("errorMessage",e.getMessage());
            System.out.println(e.getMessage());
            return "register";
        }
        return "redirect:/login";
    }
}
