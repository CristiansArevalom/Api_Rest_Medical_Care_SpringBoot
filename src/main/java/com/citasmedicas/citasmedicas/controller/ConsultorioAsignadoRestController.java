package com.citasmedicas.citasmedicas.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.citasmedicas.citasmedicas.controller.dto.ConsultorioAsignadoRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.ConsultorioAsignadoResponseDto;
import com.citasmedicas.citasmedicas.service.ConsultorioAsignadoService;

@RestController
@CrossOrigin("*")
public class ConsultorioAsignadoRestController {
    private ConsultorioAsignadoService consultorioAsignadoService;

    public ConsultorioAsignadoRestController(ConsultorioAsignadoService consultorioAsignadoService) {
        this.consultorioAsignadoService = consultorioAsignadoService;
    }

    @GetMapping("api/consultorios-asignados")
    public List<ConsultorioAsignadoResponseDto> getConsultoriosAsignados(){
        return consultorioAsignadoService.getConsultoriosAsignados();
    }
    @GetMapping("api/consultorios-asignados/consultorio/{id}")
    public List<ConsultorioAsignadoResponseDto> getConsultorioAsignadoByConsultorio(@PathVariable Long id){
        return consultorioAsignadoService.getConsultorioAsignadoByConsultorio(id);
    }
    @GetMapping("api/consultorios-asignados/especialidad/nombre={especialidad}")
    public List<ConsultorioAsignadoResponseDto> getConsultorioAsignadoByEspecialidad(@PathVariable String especialidad){
        return consultorioAsignadoService.getConsultorioAsignadoByEspecialidad(especialidad);
    }
    /*
    @GetMapping("/api/ConsultoriosAsingados/{especialidad}/{fechaInicio}/{fechaFin}")
    public List<ConsultorioAsignadoResponseDto> getConsultoriosAsignadosByEspecialidadAndAvailableByCitaDates(
        @PathVariable String especialidad, 
        @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") LocalDateTime fechaInicio,
        @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") LocalDateTime fechaFin) {
        return consultorioAsignadoService.getConsultoriosAsignadosByEspecialidadAndAvailableByCitaDates(especialidad, fechaInicio, fechaFin);
    }
 */
    @PostMapping("api/consultorios-asignados")
    public ConsultorioAsignadoResponseDto postConsultorioAsignadoRegistrar(@RequestBody ConsultorioAsignadoRequestDto consultorioAsignadoRequestDto){
        return consultorioAsignadoService.createConsultorioAsignado(consultorioAsignadoRequestDto);
        
    }
}
