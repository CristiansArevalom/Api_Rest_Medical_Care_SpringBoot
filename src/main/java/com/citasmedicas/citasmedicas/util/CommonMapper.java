package com.citasmedicas.citasmedicas.util;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.citasmedicas.citasmedicas.controller.dto.CitaMedicaResponseDto;
import com.citasmedicas.citasmedicas.controller.dto.ConsultorioAsignadoResponseDto;
import com.citasmedicas.citasmedicas.controller.dto.DoctorResponseDto;
import com.citasmedicas.citasmedicas.controller.dto.EspecialidadDto;
import com.citasmedicas.citasmedicas.controller.dto.PacienteResponseDto;
import com.citasmedicas.citasmedicas.model.entity.CitaMedica;
import com.citasmedicas.citasmedicas.model.entity.ConsultorioAsignado;
import com.citasmedicas.citasmedicas.model.entity.Doctor;
import com.citasmedicas.citasmedicas.model.entity.EnumEspecialidad;
import com.citasmedicas.citasmedicas.model.entity.Especialidad;
import com.citasmedicas.citasmedicas.model.entity.Paciente;

@Component
public class CommonMapper {

    private final ModelMapper mapper;

    public CommonMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public <T, S> S convertToDto(T obj, Class<S> dtoClazz) {
        return mapper.map(obj, dtoClazz);
    }

    public <T, S> T convertToEntity(S dto, Class<T> entityClazz) {
        return mapper.map(dto, entityClazz);
    }

    // se crea este metodo ya el entity y DTO no tienen los mismos campos ni tipo de
 
    public CitaMedicaResponseDto transformarCitaMedicaADto(CitaMedica citaMedicaDb) {
        return new CitaMedicaResponseDto(
                citaMedicaDb.getId().toString(),
                citaMedicaDb.getConsultorioAsignado().getDoctor().getNombre() + " "
                        + citaMedicaDb.getConsultorioAsignado().getDoctor().getApellido(),
                citaMedicaDb.getPaciente().getNombre() + " " + citaMedicaDb.getPaciente().getApellido(),
                citaMedicaDb.getConsultorioAsignado().getDoctor().getEspecialidad().getNombre(),
                citaMedicaDb.getFechaInicio(),
                citaMedicaDb.getFechaFin(),
                citaMedicaDb.getConsultorioAsignado().getConsultorio()
                        .getNumero());

    }

    public PacienteResponseDto transformarPacienteADto(Paciente paciente) {
        return new PacienteResponseDto(
                paciente.getId().toString(),
                paciente.getNombre(),
                paciente.getApellido(),
                paciente.getCedula(),
                paciente.getFechaNacimiento(),
                paciente.getTelefono());
    }

    public DoctorResponseDto transformarDoctorADto(Doctor doctor) {
        return new DoctorResponseDto(doctor.getId(),
                doctor.getNombre(),
                doctor.getApellido(),
                doctor.getCorreo(),
                doctor.getEspecialidad().getNombre());

    }

    public ConsultorioAsignadoResponseDto transformarConsultAsigADto(ConsultorioAsignado consultAsigDb) {
        return new ConsultorioAsignadoResponseDto(consultAsigDb.getDoctor().getId(),
                consultAsigDb.getDoctor().getNombre(), consultAsigDb.getDoctor().getApellido(),
                consultAsigDb.getDoctor().getCorreo(), consultAsigDb.getDoctor().getEspecialidad().getNombre(),
                consultAsigDb.getConsultorio().getId(), consultAsigDb.getConsultorio().getCiudad(),
                consultAsigDb.getConsultorio().getDireccion(), consultAsigDb.getConsultorio().getNumero(),
                consultAsigDb.getConsultorio().getDescripcion(), consultAsigDb.getId(),
                consultAsigDb.getInicioReserva(), consultAsigDb.getFinReserva());
    }

    public Especialidad transformarDtoAEspecialidad(EspecialidadDto especialidadDto){
        EnumEspecialidad enumEspecialidad = EnumEspecialidad.valueOf(especialidadDto.getNombre());
        return new Especialidad(especialidadDto.getId(), enumEspecialidad);   
    }


}