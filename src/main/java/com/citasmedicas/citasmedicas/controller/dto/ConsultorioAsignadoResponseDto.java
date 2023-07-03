package com.citasmedicas.citasmedicas.controller.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ConsultorioAsignadoResponseDto {
    
    private long IdDoctor;
    private String nombre;
    private String apellido;
    private String correo;
    private String NombreEspecialidad;
    private Long idConsultorio;
    private String ciudad;
    private String direccion;
    private Integer numero;
    private String descripcion;
    private Long idConsultorioAsignado;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm a")
    private LocalDateTime inicioReserva;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm a")
    private LocalDateTime finReserva;

    public ConsultorioAsignadoResponseDto(long idDoctor, String nombre, String apellido, String correo,
            String nombreEspecialidad, Long idConsultorio, String ciudad, String direccion, Integer numero,
            String descripcion, Long idConsultorioAsignado, LocalDateTime inicioReserva, LocalDateTime finReserva,
            Long idCitaMedica) {
        IdDoctor = idDoctor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        NombreEspecialidad = nombreEspecialidad;
        this.idConsultorio = idConsultorio;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.numero = numero;
        this.descripcion = descripcion;
        this.idConsultorioAsignado = idConsultorioAsignado;
        this.inicioReserva = inicioReserva;
        this.finReserva = finReserva;
    }
    
    public ConsultorioAsignadoResponseDto() {
    }
    
    public ConsultorioAsignadoResponseDto(long idDoctor, String nombre, String apellido, String correo,
            String nombreEspecialidad, Long idConsultorio, String ciudad, String direccion, Integer numero,
            String descripcion, Long idConsultorioAsignado, LocalDateTime inicioReserva, LocalDateTime finReserva) {
        IdDoctor = idDoctor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        NombreEspecialidad = nombreEspecialidad;
        this.idConsultorio = idConsultorio;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.numero = numero;
        this.descripcion = descripcion;
        this.idConsultorioAsignado = idConsultorioAsignado;
        this.inicioReserva = inicioReserva;
        this.finReserva = finReserva;
    }
    
    public long getIdDoctor() {
        return IdDoctor;
    }
    public void setIdDoctor(long idDoctor) {
        IdDoctor = idDoctor;
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
        return NombreEspecialidad;
    }
    public void setNombreEspecialidad(String nombreEspecialidad) {
        NombreEspecialidad = nombreEspecialidad;
    }
    public Long getIdConsultorio() {
        return idConsultorio;
    }
    public void setIdConsultorio(Long idConsultorio) {
        this.idConsultorio = idConsultorio;
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
    public Long getIdConsultorioAsignado() {
        return idConsultorioAsignado;
    }
    public void setIdConsultorioAsignado(Long idConsultorioAsignado) {
        this.idConsultorioAsignado = idConsultorioAsignado;
    }
    public LocalDateTime getInicioReserva() {
        return inicioReserva;
    }
    public void setInicioReserva(LocalDateTime inicioReserva) {
        this.inicioReserva = inicioReserva;
    }
    public LocalDateTime getFinReserva() {
        return finReserva;
    }
    public void setFinReserva(LocalDateTime finReserva) {
        this.finReserva = finReserva;
    }

}
