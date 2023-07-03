package com.citasmedicas.citasmedicas.controller.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ConsultorioAsignadoRequestDto {
    private Long id;
    //En el caso del DTO, estamos trabajando con datos que se van a enviar o recibir en formato JSON, por lo que la anotación @JsonFormat se utiliza para especificar el formato de fecha y hora en el JSON. 
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm") 
    private LocalDateTime inicioReserva;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime finReserva;
    private Long id_doctor;
    private Long id_consultorio;    
    public ConsultorioAsignadoRequestDto() {
    }

    public ConsultorioAsignadoRequestDto( LocalDateTime inicioReserva, LocalDateTime finReserva, Long id_doctor,
            Long id_consultorio) {
        this.inicioReserva = inicioReserva;
        this.finReserva = finReserva;
        this.id_doctor = id_doctor;
        this.id_consultorio = id_consultorio;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(Long id_doctor) {
        this.id_doctor = id_doctor;
    }

    public Long getId_consultorio() {
        return id_consultorio;
    }

    public void setId_consultorio(Long id_consultorio) {
        this.id_consultorio = id_consultorio;
    }

    

}
