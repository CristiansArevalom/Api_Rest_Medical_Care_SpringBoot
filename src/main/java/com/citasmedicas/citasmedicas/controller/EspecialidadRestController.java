package com.citasmedicas.citasmedicas.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.citasmedicas.citasmedicas.controller.dto.EspecialidadDto;
import com.citasmedicas.citasmedicas.service.EspecialidadesService;

@RestController
@CrossOrigin("*")
public class EspecialidadRestController {
    private EspecialidadesService especialidadesService;

    public EspecialidadRestController(EspecialidadesService especialidadesService){
        this.especialidadesService=especialidadesService;
    }
    @GetMapping("api/especialidades")
    public List<EspecialidadDto> getEspecialidades(){
        return especialidadesService.getEspecialidades();
    }
    @GetMapping("api/especialidades/{id}")
    public EspecialidadDto getEspecialidadById(@PathVariable("id")Long id){
        return especialidadesService.getEspecialidadById(id);
    }
    @GetMapping("api/especialidades/nombre/{nombre}")
    public EspecialidadDto getEspecialidadByNombre(@PathVariable("nombre")String nombre){
        return especialidadesService.getEspecialidadByNombre(nombre);
    }
    /*funciona como select * from especialidades where nombre like '%MEDICINA%'; se suspende hasta ver como hacerlo
    @GetMapping("api/especialidades/LikeNombre/{nombre}")
    public List<EspecialidadDto> getEspecialidadesByNombre(@PathVariable("nombre")String nombre){
        return especialidadesService.getEspecialidadesByNombre(nombre);
    } */
}
