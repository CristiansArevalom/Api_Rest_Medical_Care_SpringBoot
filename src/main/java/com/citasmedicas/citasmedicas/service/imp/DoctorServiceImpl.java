package com.citasmedicas.citasmedicas.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citasmedicas.citasmedicas.controller.dto.DoctorRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.DoctorResponseDto;
import com.citasmedicas.citasmedicas.controller.dto.EspecialidadDto;
import com.citasmedicas.citasmedicas.exceptions.DoctorDoesntExistExceptions;
import com.citasmedicas.citasmedicas.model.entity.Doctor;
import com.citasmedicas.citasmedicas.model.entity.EnumEspecialidad;
import com.citasmedicas.citasmedicas.model.entity.Especialidad;
import com.citasmedicas.citasmedicas.model.repository.DoctorRepository;
import com.citasmedicas.citasmedicas.service.DoctorService;
import com.citasmedicas.citasmedicas.service.EspecialidadesService;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    // llamo el servicio de especialidade ya que necesito traer la especialidad x
    // nombre
    private EspecialidadesService especialidadesService;
    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;

    }

    @Override // trae los doctores con su especialidad
    public List<DoctorResponseDto> getDoctores() {
        try {
            List<Doctor> doctores = doctorRepository.findAllWithEspecialidad();
            // llenando DTO doctores
            return doctores.stream().map(doctor -> new DoctorResponseDto(doctor.getId(),
                    doctor.getNombre(),
                    doctor.getApellido(),
                    doctor.getCorreo(),
                    doctor.getEspecialidad().getNombre())).collect(Collectors.toList());

        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("ERROR al buscar" + e.getStackTrace());
        }
    }

    @Override
    public DoctorResponseDto createDoctor(DoctorRequestDto doctor) {
        Doctor doctorDb = new Doctor();
        doctorDb.setNombre(doctor.getNombre());
        doctorDb.setApellido(doctor.getApellido());
        doctorDb.setCorreo(doctor.getCorreo());
        // Obtener el nombre de la especialidad del DTO
        String nombreEspecialidad = doctor.getNombreEspecialidad();
        // Buscar la especialidad por nombre en la base de datos
        EspecialidadDto especialidadDto = especialidadesService.getEspecialidadByNombre(nombreEspecialidad);
        // Si no se encuentra la especialidad, lanzar una excepción o manejar el error
        if (especialidadDto == null) {
            throw new RuntimeException("La especialidad " + nombreEspecialidad + " no existe.");
        }
        // Validar si ya existe con el correo creado para controlar el campo unico
        Optional<Doctor> doctorByEmail = doctorRepository.findByCorreo(doctor.getCorreo());
        if (doctorByEmail.isPresent()) {
            throw new DoctorDoesntExistExceptions("Error al guardar correo, ya se encuentra registrado");
        }
        // Como ya existe, se trae el EnumEspecialidad para poder crear la especialidad
        // Asignar la especialidad al Doctor
        doctorDb.setEspecialidad(
                new Especialidad(especialidadDto.getId(), EnumEspecialidad.valueOf(especialidadDto.getNombre())));

        doctorDb = doctorRepository.save(doctorDb);
        return new DoctorResponseDto(doctorDb.getId(), doctorDb.getNombre(), doctorDb.getApellido(),
                doctorDb.getCorreo(), doctorDb.getEspecialidad().getNombre());
    }

    @Override
    public DoctorResponseDto updateDoctor(DoctorRequestDto doctor) {

        Optional<Doctor> doctorOp = doctorRepository.findById(doctor.getId());
        if (doctorOp.isEmpty()) {
            throw new DoctorDoesntExistExceptions("El doctor con ese id no existe");
        }
        Doctor doctorDb = doctorOp.get();
        doctorDb.setNombre(doctor.getNombre());
        doctorDb.setApellido(doctor.getApellido());
        doctorDb.setCorreo(doctor.getCorreo());
        // Obtener el nombre de la especialidad del DTO
        String nombreEspecialidad = doctor.getNombreEspecialidad();
        // Buscar la especialidad por nombre en la base de datos
        EspecialidadDto especialidadDto = especialidadesService.getEspecialidadByNombre(nombreEspecialidad);
        // Si no se encuentra la especialidad, lanzar una excepción o manejar el error
        if (especialidadDto == null) {
            throw new RuntimeException("La especialidad " + nombreEspecialidad + " no existe.");
        }
        // Como ya existe, se trae el EnumEspecialidad para poder crear la especialidad
        // Asignar la especialidad al Doctor
        doctorDb.setEspecialidad(
                new Especialidad(especialidadDto.getId(), EnumEspecialidad.valueOf(especialidadDto.getNombre())));
        doctorDb = doctorRepository.save(doctorDb);
        return new DoctorResponseDto(doctorDb.getId(), doctorDb.getNombre(), doctorDb.getApellido(),
                doctorDb.getCorreo(), doctorDb.getEspecialidad().getNombre());

    }

    @Override
    public void deleteDoctor(Long id) {
        Optional<Doctor> doctorOp = doctorRepository.findById(id);
        if (doctorOp.isEmpty()) {
            throw new DoctorDoesntExistExceptions("El doctor no existe");
        }
        Doctor doctorDb = doctorOp.get();
        doctorRepository.delete(doctorDb);

    }

    @Override
    public DoctorResponseDto getDoctoresById(Long id) {
        Optional<Doctor> doctorOp = doctorRepository.findById(id);
        if (doctorOp.isEmpty()) {
            throw new DoctorDoesntExistExceptions("El doctor con ese Id no existe");
        }
        Doctor doctorDb = doctorOp.get();
        return new DoctorResponseDto(doctorDb.getId(), doctorDb.getNombre(), doctorDb.getApellido(),
                doctorDb.getCorreo(), doctorDb.getEspecialidad().getNombre());
    }

    @Override
    public List<DoctorResponseDto> getDoctoresByEspecialidad(String especialidad) {
        // buscar priemro si existe la especialidad en la clase especialidad y la crea
        EspecialidadDto especialidadOp = especialidadesService.getEspecialidadByNombre(especialidad);
        Especialidad especialidadDb = new Especialidad(especialidadOp.getId(),
                EnumEspecialidad.valueOf(especialidadOp.getNombre()));
        List<Doctor> doctores = doctorRepository.findAllByEspecialidad(especialidadDb);
        if (doctores.size() == 0) {
            throw new DoctorDoesntExistExceptions("No existen doctores con esa especialidad");
        }
        return doctores.stream().map(doctor -> new DoctorResponseDto(doctor.getId(),
                doctor.getNombre(),
                doctor.getApellido(),
                doctor.getCorreo(),
                doctor.getEspecialidad().getNombre())).collect(Collectors.toList());
    }

}
