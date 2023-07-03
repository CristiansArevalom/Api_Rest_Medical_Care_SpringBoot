package com.citasmedicas.citasmedicas.service;


import java.util.List;
import com.citasmedicas.citasmedicas.controller.dto.ConsultorioDto;

public interface ConsultorioService {
    List<ConsultorioDto> getConsultorios();
    ConsultorioDto getConsultorioById(Long id);
    ConsultorioDto createConsultorio(ConsultorioDto consultorioDto);
    ConsultorioDto updateConsultorio(ConsultorioDto consultorioDto);
    void deleteConsultorio(Long id);
    List<ConsultorioDto> getConsultoriosDisponibles(String fechaInicio,String fechaFin);
}
