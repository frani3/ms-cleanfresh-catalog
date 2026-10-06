package com.cleanfresh.ms_cleanfresh_catalog.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;

import java.util.LinkedHashMap;
import java.util.Map;

@Entity
@Table(name = "servicios")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Double precio;
    private Double duracionHoras;
    private Boolean disponible;

    // Disponibilidad por sucursal: una fila (servicio, sucursal, habilitada)
    // en la tabla servicio_sucursal, para que el JSON siga siendo el mapa de EP1.
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "servicio_sucursal", joinColumns = @JoinColumn(name = "servicio_id"))
    @MapKeyColumn(name = "sucursal")
    @Column(name = "habilitada")
    private Map<String, Boolean> sucursales = new LinkedHashMap<>();

    protected ServiceEntity() {
    }

    public ServiceEntity(String nombre, String descripcion, Double precio, Double duracionHoras,
                         Boolean disponible, Map<String, Boolean> sucursales) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracionHoras = duracionHoras;
        this.disponible = disponible;
        this.sucursales = new LinkedHashMap<>(sucursales);
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public Double getDuracionHoras() {
        return duracionHoras;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public Map<String, Boolean> getSucursales() {
        return sucursales;
    }
}
