package com.citasmedicas.citasmedicas.controller.dto;

public class DoctorRequestDto {
    
    private long Id;
    private String nombre;
    private String apellido;
    private String correo;
    private String nombreEspecialidad;
    public DoctorRequestDto() {
    }
    public DoctorRequestDto(long id, String nombre, String apellido, String correo, String nombreEspecialidad) {
        Id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.nombreEspecialidad = nombreEspecialidad;
    }
    public long getId() {
        return Id;
    }
    public void setId(long id) {
        Id = id;
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
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }
    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    
 

    

}
