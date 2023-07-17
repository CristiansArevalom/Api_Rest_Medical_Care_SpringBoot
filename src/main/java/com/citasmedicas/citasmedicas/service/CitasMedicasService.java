package com.citasmedicas.citasmedicas.service;

import java.util.List;

import com.citasmedicas.citasmedicas.controller.dto.CitaMedicaRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.CitaMedicaResponseDto;

/*cita medica podria tener los PK de doctores y consultorios para intentar evitar esa tabla intermedia de consultorio asignado
*consultorio asignado tiene mucha carga y d apoco valor, citas medicas debio tener id paciente, id doctor e ind consultorio. 
solucion: hacer agregacón  (Doctor y )

teniendo la cita medica, s epuede hacer el mapeo pa traer el paciente y consult asig


cita medica podria tener los PK de doctores y consultorios para intentar evitar esa tabla intermedia de consultorio asignado
*consultorio asignado tiene mucha carga y d apoco valor, citas medicas debio tener id paciente, id doctor e ind consultorio. 
solucion: hacer agregacón  (Doctor y )

teniendo la cita medica, s epuede hacer el mapeo pa traer el paciente y consult asig


OPCION D EMEJORA: HACER EL MAPEO MEJOR CON PACIENTE. 

 */

public interface CitasMedicasService {
    List<CitaMedicaResponseDto> getCitasMedias();
    CitaMedicaResponseDto getCitasMedicasById(Long id);
    List<CitaMedicaResponseDto> getCitasMedicasByConsultorioAsignadoId(Long id);
    List<CitaMedicaResponseDto> getCitasMedicasByDoctorId(Long id);
    List<CitaMedicaResponseDto> getCitasMedicasByPacienteId(Long id);
    List<CitaMedicaResponseDto> getCitasMedicasByNombreEspecialidad(String id);
    CitaMedicaResponseDto createCitaMedica(CitaMedicaRequestDto citaMedicaRequestDto);

}
