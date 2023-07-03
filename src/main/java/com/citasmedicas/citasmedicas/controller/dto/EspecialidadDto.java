package com.citasmedicas.citasmedicas.controller.dto;

public class EspecialidadDto {
    private Long id;
    private String nombre; //los datos los debe traer de un ENUM 
    private String descripcion;//los datos los debe traer de un ENUM 
   
    public EspecialidadDto() {
    }
    public EspecialidadDto(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
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
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
