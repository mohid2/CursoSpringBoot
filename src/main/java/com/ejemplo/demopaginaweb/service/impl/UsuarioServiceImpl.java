package com.ejemplo.demopaginaweb.service.impl;

import com.ejemplo.demopaginaweb.entity.Usuario;
import com.ejemplo.demopaginaweb.repository.IUsuarioRepository;
import com.ejemplo.demopaginaweb.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private IUsuarioRepository iUsuarioRepository;
    @Autowired
    public UsuarioServiceImpl(IUsuarioRepository iUsuarioRepository) {
        this.iUsuarioRepository = iUsuarioRepository;
    }

    @Override
    public Usuario createUser(Usuario usuario) throws Exception {
        return iUsuarioRepository.save(usuario);
    }
}
