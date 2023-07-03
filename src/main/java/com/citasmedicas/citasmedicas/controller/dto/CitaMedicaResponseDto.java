package com.citasmedicas.citasmedicas.controller.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CitaMedicaResponseDto {
        private String id;
        private String doctor;
        private String paciente;
        private String especialidad;  //ESTABLECER LOGICA PARA QUE ESTO SE LLENDE DE LO QUE TRAIGA EL DOCTOR
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm") //En el caso del DTO, estamos trabajando con datos que se van a enviar o recibir en formato JSON, por lo que la anotación @JsonFormat se utiliza para especificar el formato de fecha y hora en el JSON. 
        private LocalDateTime fechaInicio;
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm") //En el caso del DTO, estamos trabajando con datos que se van a enviar o recibir en formato JSON, por lo que la anotación @JsonFormat se utiliza para especificar el formato de fecha y hora en el JSON. 
        private LocalDateTime fechaFin;
        private Integer consultorio;

        public CitaMedicaResponseDto(String id, String doctor, String paciente, String especialidad,
                LocalDateTime fechaInicio, LocalDateTime fechaFin, Integer consultorio) {
            this.id = id;
            this.doctor = doctor;
            this.paciente = paciente;
            this.especialidad = especialidad;
            this.fechaInicio = fechaInicio;
            this.fechaFin = fechaFin;
            this.consultorio = consultorio;
        }

        public CitaMedicaResponseDto() {
        }

        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getDoctor() {
            return doctor;
        }
        public void setDoctor(String doctor) {
            this.doctor = doctor;
        }
        public String getPaciente() {
            return paciente;
        }
        public void setPaciente(String paciente) {
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
        public Integer getConsultorio() {
            return consultorio;
        }
        public void setConsultorio(Integer consultorio) {
            this.consultorio = consultorio;
        }
    
}
