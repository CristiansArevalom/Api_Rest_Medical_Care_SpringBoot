package com.citasmedicas.citasmedicas.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.citasmedicas.citasmedicas.controller.dto.ConsultorioDto;
import com.citasmedicas.citasmedicas.service.ConsultorioService;

@RestController
@CrossOrigin("*")
public class ConsultorioRestController {
    private ConsultorioService consultorioService;

    public ConsultorioRestController(ConsultorioService consultorioService) {
        this.consultorioService = consultorioService;
    }
    
    @GetMapping("api/consultorios")
    public List<ConsultorioDto> getConsultorios(){
        return consultorioService.getConsultorios();
    }
    @GetMapping("api/consultorios/reserva/fecha-inicio={fechaInicio}&fecha-fin={fechaFin}")
    public List<ConsultorioDto> getConsultoriosDisponibles(@PathVariable("fechaInicio")String fechaInicioStr,@PathVariable("fechaFin")String fechaFinStr){
        return consultorioService.getConsultoriosDisponibles(fechaInicioStr, fechaFinStr);
    }
    @PostMapping("api/consultorios")
    public ConsultorioDto postConsultoriosRegistrar(@RequestBody ConsultorioDto consultorioDto){
        return consultorioService.createConsultorio(consultorioDto);

    }
    @PutMapping("api/consultorios/{id}")
    public ConsultorioDto putConsultoriosUpdate(@PathVariable("id") Long id,@RequestBody ConsultorioDto consultorioDto){
        consultorioDto.setId(id); // pra no tener que colocar el id en el body, sino solo en la url
        return consultorioService.updateConsultorio(consultorioDto);
    }

    @GetMapping("api/consultorios/{id}")
    public ConsultorioDto getConsultoriosById(@PathVariable("id") Long id){
        return consultorioService.getConsultorioById(id);
    }
    @DeleteMapping("api/consultorios/{id}")
    public ResponseEntity<String> deleteConsultorioById(@PathVariable("id") Long id){
        consultorioService.deleteConsultorio(id);
        return ResponseEntity.ok("Consultorio eliminado correctamente");
    }

}
