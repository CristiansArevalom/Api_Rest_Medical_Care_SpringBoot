package com.citasmedicas.citasmedicas.model.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="pacientes")
public class Paciente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    @Column(nullable = false,unique = true)
    //private Long cedula;
    private String cedula;
    @Column(nullable = false)
    private LocalDate FechaNacimiento;
    @Column(nullable = false,unique=true)
    private String telefono;

    @OneToMany(mappedBy = "paciente",cascade=CascadeType.ALL, orphanRemoval = true) //si s eborra un paciente, se borra todos los registros relacionado con el en las demas tablas
    private List<CitaMedica> citas_medicas;

    public Paciente() {
    }
    public Paciente(Long id, String nombre, String apellido, String cedula, LocalDate FechaNacimiento, String telefono) {
        Id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.FechaNacimiento = FechaNacimiento;
        this.telefono = telefono;
    }
    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
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
    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public LocalDate getFechaNacimiento() {
        return FechaNacimiento;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
    
}
