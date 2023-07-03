package com.citasmedicas.citasmedicas.service;

import java.util.List;

import com.citasmedicas.citasmedicas.controller.dto.CitaMedicaRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.CitaMedicaResponseDto;

public interface CitasMedicasService {
    List<CitaMedicaResponseDto> getCitasMedias();
    CitaMedicaResponseDto getCitasMedicasById(Long id);
    List<CitaMedicaResponseDto> getCitasMedicasByConsultorioAsignadoId(Long id);
    List<CitaMedicaResponseDto> getCitasMedicasByDoctorId(Long id);
    List<CitaMedicaResponseDto> getCitasMedicasByPacienteId(Long id);
    List<CitaMedicaResponseDto> getCitasMedicasByNombreEspecialidad(String id);
    CitaMedicaResponseDto createCitaMedica(CitaMedicaRequestDto citaMedicaRequestDto);

}
