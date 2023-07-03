package com.citasmedicas.citasmedicas.controller.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PacienteResponseDto {
    private String id;
    private String nombre;
    private String apellido;
    private String cedula;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento; 
    private String telefono;
    private Integer edad;
    
    public PacienteResponseDto() {
    }
    
    public PacienteResponseDto(String id,String nombre, String apellido, String cedula, LocalDate fechaNacimiento, String telefono) {
        this.id=id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.edad=LocalDate.now().getYear()-fechaNacimiento.getYear();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public Integer getEdad() {
        return edad;
    }
}
