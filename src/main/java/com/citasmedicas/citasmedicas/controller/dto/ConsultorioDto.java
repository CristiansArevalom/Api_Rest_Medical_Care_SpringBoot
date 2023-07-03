package com.citasmedicas.citasmedicas.controller.dto;

public class ConsultorioDto {
    private Long id;
    private String ciudad;
    private String direccion;
    private Integer numero;
    private String descripcion;
    public ConsultorioDto() {
    }
    public ConsultorioDto(String ciudad, String direccion, Integer numero, String descripcion) {
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.numero = numero;
        this.descripcion = descripcion;
    }
    
    public ConsultorioDto(Long id, String ciudad, String direccion, Integer numero, String descripcion) {
        this.id = id;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.numero = numero;
        this.descripcion = descripcion;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public Integer getNumero() {
        return numero;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    
}
