package com.ejemplo.demopaginaweb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import jakarta.xml.bind.annotation.XmlTransient;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "facturas")
public class Factura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String descripcion;
    private String observacion;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyyy-MM-dd")
    private LocalDate fecha;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Cliente cliente;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "factura_id")
    @JsonManagedReference
    private Set<ItemFactura> itemFacturas;

    public Factura() {
        itemFacturas = new HashSet<>() ;
    }


    @PrePersist
    public void prePersist() {
        this.fecha = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<ItemFactura> getItemFacturas() {
        return itemFacturas;
    }

    public void setItemFacturas(Set<ItemFactura> itemFacturas) {
        this.itemFacturas = itemFacturas;
    }

    public void addItems(ItemFactura itemFactura) {
        this.itemFacturas.add(itemFactura);
    }

    public Double calcularTotal() {
        BigDecimal number = new BigDecimal(itemFacturas.stream().mapToDouble((ItemFactura::calcularImporte)).sum());
        int decimals = 2;
        BigDecimal rounded = number.setScale(decimals, RoundingMode.HALF_UP);
        return rounded.doubleValue();
    }
}
