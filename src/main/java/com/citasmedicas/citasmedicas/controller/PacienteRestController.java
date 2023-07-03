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

import com.citasmedicas.citasmedicas.controller.dto.PacienteRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.PacienteResponseDto;
import com.citasmedicas.citasmedicas.service.PacienteService;

@RestController
@CrossOrigin("*")
public class PacienteRestController {
    private PacienteService pacienteService;

    public PacienteRestController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("api/pacientes")
    public List<PacienteResponseDto> getPacientes(){
        return pacienteService.getPacientes();
    }
    @GetMapping("api/pacientes/{id}")
    public PacienteResponseDto getPacientes(@PathVariable("id")Long id){
        return pacienteService.getPacienteById(id);
    }
    @GetMapping("api/pacientes/cedula/{id}")
    public PacienteResponseDto getPacientesByCedula(@PathVariable("id")String cedula){
        return pacienteService.getPacienteByCedula(cedula);
    }
    @PostMapping("api/pacientes")
    public PacienteResponseDto postPacientesRegister(@RequestBody PacienteRequestDto paciente){
        return pacienteService.createPaciente(paciente);

    }
    
    @PutMapping("api/pacientes/{id}")
    public PacienteResponseDto putPacientesUpdate(@PathVariable("id") Long id, @RequestBody PacienteRequestDto paciente){
        paciente.setId(id); // pra no tener que colocar el id en el body, sino solo en la url
        return pacienteService.updatePaciente(paciente);
    }
    
    @DeleteMapping("api/pacientes/{id}")
    public ResponseEntity<String> deletePaciente(@PathVariable("id")Long id){
        pacienteService.deletePaciente(id);
        return ResponseEntity.ok("paciente eliminado correctamente");

    } 

    
}
