package com.ejemplo.demopaginaweb.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String firstname;
    @Column(name = "apellido")
    private String lastname;
    @Column(name = "correo")
    private String email;
    @Column(name = "nombre_usuario",unique = true,length = 30)
    private String nombreUsuario;
    @Column(name = "contraseña",length = 60)
    private String password;
    private boolean activo;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL)
    private Set<Role> roles=new HashSet<>() ;

    public Usuario() {

    }

    public Usuario(Long id, String firstname, String lastname, String email, String nombreUsuario, String password, boolean activo, Set<Role> roles) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.activo = activo;
        this.roles = roles;
    }

    public Usuario(Long id, String nombreUsuario, String password, boolean activo, Set<Role> roles) {
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public void addRole(Role roles) {
        this.roles.add(roles);
    }

}
