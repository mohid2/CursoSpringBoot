package com.ejemplo.demopaginaweb.service;

import com.ejemplo.demopaginaweb.entity.Usuario;

import java.util.Optional;

public interface IUsuarioService {
    Usuario createUser(Usuario usuario) throws Exception;
    Optional<Usuario> getUserByUsername(String name);
    void deleteUser(Usuario usuario);
}
