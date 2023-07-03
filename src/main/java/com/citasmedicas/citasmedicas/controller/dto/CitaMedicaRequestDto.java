package com.citasmedicas.citasmedicas.controller.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CitaMedicaRequestDto {
    
    private String cedulaPaciente;
    private String especialidad;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm") //En el caso del DTO, estamos trabajando con datos que se van a enviar o recibir en formato JSON, por lo que la anotación @JsonFormat se utiliza para especificar el formato de fecha y hora en el JSON. 
    private LocalDateTime fechaInicio;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm") //En el caso del DTO, estamos trabajando con datos que se van a enviar o recibir en formato JSON, por lo que la anotación @JsonFormat se utiliza para especificar el formato de fecha y hora en el JSON. 
    private LocalDateTime fechaFin;


    public CitaMedicaRequestDto() {
    }

    public CitaMedicaRequestDto(String cedulaPaciente, String especialidad, LocalDateTime fechaInicio,
            LocalDateTime fechaFin) {
        this.cedulaPaciente = cedulaPaciente;
        this.especialidad = especialidad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }



    public String getCedulaPaciente() {
        return cedulaPaciente;
    }
    public void setCedulaPaciente(String cedulaPaciente) {
        this.cedulaPaciente = cedulaPaciente;
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

}
