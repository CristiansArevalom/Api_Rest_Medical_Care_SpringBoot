package com.citasmedicas.citasmedicas.model.entity;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="citas_medicas")
public class CitaMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="id_paciente",foreignKey = @ForeignKey(name="CITA_MED_ID_PACIENTE_FK"))
    private Paciente paciente;
    @Column(nullable = false)
    private String especialidad;
    @DateTimeFormat(pattern="dd-MM-yyyy HH:mm")
    @Column(nullable = false)
    private LocalDateTime fechaInicio;
    @Column(nullable = false)
    @DateTimeFormat(pattern="dd-MM-yyyy HH:mm")
    private LocalDateTime fechaFin;
    @ManyToOne
    @JoinColumn(name="id_consultorios_asignados",foreignKey = @ForeignKey(name="CITAMEDICA_ID_CONSULT_ASIG_FK"))
    private ConsultorioAsignado consultorioAsignado;
    

    public CitaMedica() {
    }
    public CitaMedica(Paciente paciente, String especialidad, LocalDateTime fechaInicio,
            LocalDateTime fechaFin,ConsultorioAsignado consultorioAsignado) {
        this.paciente = paciente;
        this.especialidad = especialidad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.consultorioAsignado=consultorioAsignado;

    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public LocalDateTime getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }
    public ConsultorioAsignado getConsultorioAsignado() {
        return consultorioAsignado;
    }
    public void setConsultorioAsignado(ConsultorioAsignado consultorioAsignado) {
        this.consultorioAsignado = consultorioAsignado;
    }
    
}