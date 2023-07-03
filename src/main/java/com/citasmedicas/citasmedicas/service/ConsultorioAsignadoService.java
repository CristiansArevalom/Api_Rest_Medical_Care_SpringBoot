package com.citasmedicas.citasmedicas.service;

import java.util.List;

import com.citasmedicas.citasmedicas.controller.dto.ConsultorioAsignadoRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.ConsultorioAsignadoResponseDto;

public interface ConsultorioAsignadoService {
    List<ConsultorioAsignadoResponseDto> getConsultoriosAsignados();
    List<ConsultorioAsignadoResponseDto> getConsultorioAsignadoByConsultorio(Long id);
    List<ConsultorioAsignadoResponseDto> getConsultorioAsignadoByEspecialidad(String especialidad);
    ConsultorioAsignadoResponseDto createConsultorioAsignado(ConsultorioAsignadoRequestDto consultorioAsignadoRequestDto);
}
