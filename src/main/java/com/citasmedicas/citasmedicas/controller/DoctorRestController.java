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

import com.citasmedicas.citasmedicas.controller.dto.DoctorRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.DoctorResponseDto;
import com.citasmedicas.citasmedicas.service.DoctorService;

@RestController
@CrossOrigin("*")
public class DoctorRestController {
    private DoctorService doctorService;
    
    public DoctorRestController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("api/doctores")
    public List<DoctorResponseDto> getDoctores(){
        return doctorService.getDoctores();
    }
    @GetMapping("api/doctores/{id}")
    public DoctorResponseDto getDoctor(@PathVariable("id") Long id){
        return doctorService.getDoctoresById(id);
    }
    @PutMapping("api/doctores/{id}")
    public DoctorResponseDto putDoctorUpdate(@PathVariable("id") Long id, @RequestBody DoctorRequestDto doctorRequestDto){
        doctorRequestDto.setId(id);
        return doctorService.updateDoctor(doctorRequestDto);
    }

    @PostMapping("api/doctores")
    public DoctorResponseDto postDoctoresRegister(@RequestBody DoctorRequestDto doctor){
        return doctorService.createDoctor(doctor);
    }

    @DeleteMapping("api/doctores/{id}")
    public ResponseEntity <String> deleteDoctor(@PathVariable("id") Long id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok("Doctor eliminado correctamente");
    }
    @GetMapping("api/doctores/especialidad/{especialidad}")
    public List<DoctorResponseDto> getDoctoresByEspecialidad(@PathVariable("especialidad") String especialidad){
        return doctorService.getDoctoresByEspecialidad(especialidad);
    }
}
