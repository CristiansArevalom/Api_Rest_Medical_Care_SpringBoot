package com.citasmedicas.citasmedicas.service;

import java.util.List;

import com.citasmedicas.citasmedicas.controller.dto.PacienteRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.PacienteResponseDto;

public interface PacienteService {
    
    List<PacienteResponseDto> getPacientes();
    PacienteResponseDto getPacienteById(Long Id);
    PacienteResponseDto getPacienteByCedula(String cedula);
    PacienteResponseDto createPaciente(PacienteRequestDto paciente);
    PacienteResponseDto updatePaciente(PacienteRequestDto paciente);
    void deletePaciente(Long id);

}
