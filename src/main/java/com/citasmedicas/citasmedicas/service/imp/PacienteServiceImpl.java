package com.citasmedicas.citasmedicas.service.imp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.citasmedicas.citasmedicas.controller.dto.PacienteRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.PacienteResponseDto;
import com.citasmedicas.citasmedicas.exceptions.PacienteAlreadyExistExceptions;
import com.citasmedicas.citasmedicas.exceptions.PacienteDoesntExistExceptions;
import com.citasmedicas.citasmedicas.model.entity.Paciente;
import com.citasmedicas.citasmedicas.model.repository.PacienteRepository;
import com.citasmedicas.citasmedicas.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService {
    private final PacienteRepository pacienteRepository;


    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }


    @Override
    public List<PacienteResponseDto> getPacientes() {
        try{
        List<Paciente> pacientes = pacienteRepository.findAll();

        //llenando el DTO de paciente
        return pacientes.stream().map(paciente -> new PacienteResponseDto(
            paciente.getId().toString(),
            paciente.getNombre(), 
            paciente.getApellido(),
             paciente.getCedula(), 
             paciente.getFechaNacimiento(), 
             paciente.getTelefono())).collect(Collectors.toList());
        }catch(Exception ex){
            throw new UnsupportedOperationException("Error al obtener pacientes" +ex);

        }          
    }


    @Override
    public PacienteResponseDto createPaciente(PacienteRequestDto paciente) {
   
        Optional <Paciente> pacienteOpc = pacienteRepository.findByCedula(paciente.getCedula());
        Optional <Paciente> pacienteOpcTel = pacienteRepository.findByTelefono(paciente.getTelefono());
        if(pacienteOpc.isPresent()){
            throw new PacienteAlreadyExistExceptions("Paciente ya registrado");
        }else if(paciente.getFechaNacimiento().isAfter(LocalDate.now())){
            throw new PacienteDoesntExistExceptions("Fecha de nacimiento Invalida, no puede ser superior a la fecha actual");
        }
        
        else if(pacienteOpcTel.isPresent()){
            throw new PacienteAlreadyExistExceptions("telefono ya registrado");
        }else if(paciente.getCedula().length()<8 && paciente.getCedula().length()>10){
            throw new PacienteDoesntExistExceptions("Cedula ingresada invalida, debe tener de 8 a 10 caracteres");
        }

        Paciente pacienteDb = new Paciente();
        pacienteDb.setNombre(paciente.getNombre());
        pacienteDb.setApellido(paciente.getApellido());
        pacienteDb.setCedula(paciente.getCedula());
        pacienteDb.setFechaNacimiento(paciente.getFechaNacimiento());
        pacienteDb.setTelefono(paciente.getTelefono());
        pacienteDb=pacienteRepository.save(pacienteDb);//usa el save que trae JPA

        return new PacienteResponseDto(pacienteDb.getId().toString(),pacienteDb.getNombre(), 
        pacienteDb.getApellido(), 
        pacienteDb.getCedula(), 
        pacienteDb.getFechaNacimiento(),
        pacienteDb.getTelefono());
    }


    @Override
    public PacienteResponseDto updatePaciente(PacienteRequestDto paciente) {
        Optional<Paciente> pacienteOp = pacienteRepository.findById(paciente.getId());
        if(pacienteOp.isEmpty()){
            throw new PacienteDoesntExistExceptions("El paciente indicado no existe");
        }
        //validar si exise paciente ya con dicha info
        Optional <Paciente> pacienteOpCedula = pacienteRepository.findByCedula(paciente.getCedula());
        if(pacienteOpCedula.isPresent()){
            if(pacienteOpCedula.get().getId()!=paciente.getId()){
            throw new PacienteAlreadyExistExceptions("Paciente ya registrado con esa cedula");
            }
        }


        Paciente pacienteDb = (Paciente) pacienteOp.get();
        pacienteDb.setNombre(paciente.getNombre());
        pacienteDb.setApellido(paciente.getApellido());
        pacienteDb.setCedula(paciente.getCedula());
        pacienteDb.setFechaNacimiento(paciente.getFechaNacimiento());
        pacienteDb.setTelefono(paciente.getTelefono());
        pacienteDb=pacienteRepository.save(pacienteDb);
        return new PacienteResponseDto(paciente.getId().toString(),pacienteDb.getNombre(), 
            pacienteDb.getApellido(), 
            pacienteDb.getCedula(), 
            pacienteDb.getFechaNacimiento(),
            pacienteDb.getTelefono());
    }


    @Override
    public void deletePaciente(Long id) {
        Optional <Paciente>pacienteOp =pacienteRepository.findById(id);
        if(pacienteOp.isEmpty()){
            throw new PacienteDoesntExistExceptions("El paciente no existe");
        }
        Paciente pacienteDb = (Paciente) pacienteOp.get();
        pacienteRepository.delete(pacienteDb);
    }


    @Override
    public PacienteResponseDto getPacienteById(Long Id) {
       Optional <Paciente> pacienteOp = pacienteRepository.findById(Id);
       if(pacienteOp.isEmpty()){
        throw new PacienteDoesntExistExceptions("El paciente no existe");
       }
       Paciente pacienteDb = pacienteOp.get();
        PacienteResponseDto paciente = new PacienteResponseDto(pacienteDb.getId().toString(),pacienteDb.getNombre(), 
        pacienteDb.getApellido(), 
        pacienteDb.getCedula(), 
        pacienteDb.getFechaNacimiento(),
        pacienteDb.getTelefono());
       return paciente;
    }


    @Override
    public PacienteResponseDto getPacienteByCedula(String cedula) {
        Optional <Paciente> pacienteOp = pacienteRepository.findByCedula(cedula);
        if(pacienteOp.isEmpty()){
         throw new PacienteDoesntExistExceptions("El paciente no existe");
        }
        Paciente pacienteDb = pacienteOp.get();
         PacienteResponseDto paciente = new PacienteResponseDto(pacienteDb.getId().toString(),pacienteDb.getNombre(), 
         pacienteDb.getApellido(), 
         pacienteDb.getCedula(), 
         pacienteDb.getFechaNacimiento(),
         pacienteDb.getTelefono());
        return paciente;
    }
    
    
}
