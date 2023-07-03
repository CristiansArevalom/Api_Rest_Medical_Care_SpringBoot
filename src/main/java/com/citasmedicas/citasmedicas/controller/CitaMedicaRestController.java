package com.citasmedicas.citasmedicas.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.citasmedicas.citasmedicas.controller.dto.CitaMedicaRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.CitaMedicaResponseDto;
import com.citasmedicas.citasmedicas.service.CitasMedicasService;

@RestController
@CrossOrigin("*")
public class CitaMedicaRestController {
    private CitasMedicasService citasMedicasService;

    public CitaMedicaRestController(CitasMedicasService citasMedicasService){
        this.citasMedicasService=citasMedicasService;
    }
    @GetMapping("api/citas-medicas/consultorio-asignado/{id}")
    public List<CitaMedicaResponseDto> getCitasMedicasByConsultorioAsignadoId(@PathVariable("id")Long id){
        return citasMedicasService.getCitasMedicasByConsultorioAsignadoId(id);
    }
    @GetMapping("api/citas-medicas")
    public List<CitaMedicaResponseDto> getCitasMedicas(){
        return citasMedicasService.getCitasMedias();
    }
    @GetMapping("/api/citas-medicas/paciente/{id}")
    public List<CitaMedicaResponseDto> getCitasMedicasByPacienteId(@PathVariable("id")Long id){
        return citasMedicasService.getCitasMedicasByPacienteId(id);
    }
    @GetMapping("/api/citas-medicas/doctor/{id}")
    public List<CitaMedicaResponseDto> getCitasMedicasByDoctorId(@PathVariable("id")Long id){
        return citasMedicasService.getCitasMedicasByDoctorId(id);
    }
        @GetMapping("/api/citas-medicas/especialidad/{especialidad}")
    public List<CitaMedicaResponseDto> getCitasMedicasByEspecialidad(@PathVariable("especialidad")String especialidad){
        return citasMedicasService.getCitasMedicasByNombreEspecialidad(especialidad);
    }

    @PostMapping("api/citas-medicas")
    public CitaMedicaResponseDto saveCitaMedica(@RequestBody CitaMedicaRequestDto citaMedicaRequestDto){

        return citasMedicasService.createCitaMedica(citaMedicaRequestDto);
    }
}
