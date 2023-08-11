package com.ejemplo.demopaginaweb.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_usuario",unique = true,length = 30)
    private String nombreUsuario;
    @Column(length = 60)
    private String password;
    private boolean activo;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Role> roles=new ArrayList<>();

    public Usuario() {

    }

    public Usuario(Long id, String nombreUsuario, String password, boolean activo, List<Role> roles) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.activo = activo;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    public void addRole(Role roles) {
        this.roles.add(roles);
    }

}
