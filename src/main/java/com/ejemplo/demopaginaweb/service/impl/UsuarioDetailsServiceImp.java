package com.ejemplo.demopaginaweb.service.impl;

import com.ejemplo.demopaginaweb.entity.Role;
import com.ejemplo.demopaginaweb.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional()
public class UsuarioDetailsServiceImp implements UserDetailsService {

    private final IUsuarioRepository iUsuarioRepository;

    @Autowired
    public UsuarioDetailsServiceImp(IUsuarioRepository iUsuarioRepository) {
        this.iUsuarioRepository = iUsuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      var opUser= iUsuarioRepository.findByNombreUsuario(username);
      if(!opUser.isPresent()){
          System.out.println("el u no exsiste");
          throw new UsernameNotFoundException("el u no exsiste");
      }
        List<GrantedAuthority> roles=new ArrayList<>();
        for (Role role:opUser.get().getRoles()){
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }
        System.out.println(opUser.get().getNombreUsuario()+" "+opUser.get().getPassword());

        UserDetails userDetails =new User(opUser.get().getNombreUsuario(),opUser.get().getPassword(),roles);
        return userDetails;
    }
}
