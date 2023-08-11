package com.ejemplo.demopaginaweb.repository;

import com.ejemplo.demopaginaweb.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByNombreUsuario(String username);
}
