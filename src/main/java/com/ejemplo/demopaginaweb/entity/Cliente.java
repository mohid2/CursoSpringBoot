package com.ejemplo.demopaginaweb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String nombre;
    @NotEmpty
    private String apellidos;
    @NotEmpty
    @Email
    private String email;
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyyy-MM-dd")
    @NotNull
    private  LocalDate fechaAlta;
    private String foto;
    @OneToMany(mappedBy ="cliente" ,fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Factura> facturas;

    public Cliente() {
        this.facturas = new ArrayList<>() ;
    }

    public Cliente(Long id, String nombre, String apellidos, String email,  LocalDate fechaAlta, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.fechaAlta = fechaAlta;
        this.foto = foto;
        this.facturas = new ArrayList<>() ;
    }
    @PrePersist
    public void prePersist() {
        this.fechaAlta = LocalDate.now();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public  LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta( LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

}
