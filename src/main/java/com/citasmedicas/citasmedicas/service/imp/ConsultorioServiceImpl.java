package com.citasmedicas.citasmedicas.service.imp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.citasmedicas.citasmedicas.controller.dto.ConsultorioDto;
import com.citasmedicas.citasmedicas.exceptions.ConsultorioAlreadyExistException;
import com.citasmedicas.citasmedicas.exceptions.ConsultorioDoesntExistException;
import com.citasmedicas.citasmedicas.model.entity.Consultorio;
import com.citasmedicas.citasmedicas.model.repository.ConsultorioRepository;
import com.citasmedicas.citasmedicas.service.ConsultorioService;
import com.citasmedicas.citasmedicas.util.CommonMapper;

@Service
public class ConsultorioServiceImpl implements ConsultorioService {
    private static final LocalDateTime currentDate = LocalDateTime.now();
    private final CommonMapper mapper;

    private final ConsultorioRepository consultorioRepository;

    public ConsultorioServiceImpl(ConsultorioRepository consultorioRepository,
            CommonMapper mapper) {
        this.consultorioRepository = consultorioRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ConsultorioDto> getConsultorios() {
        try {
            List<Consultorio> consultorios = consultorioRepository.findAll();
            return consultorios.stream()
                    // en este caso se usa la conversion a dto usando mapper ya que los atributos
                    // del dto y entity son iguales
                    .map(consultorio -> mapper.convertToDto(consultorio, ConsultorioDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'getConsultorios'");
        }

    }

    @Override
    public ConsultorioDto createConsultorio(ConsultorioDto consultorioDto) {
        try {
            List<Consultorio> consultorioByDireccion = consultorioRepository
                    .findByCiudadAndDescripcionAndDireccionAndNumero(consultorioDto.getCiudad(),
                            consultorioDto.getDescripcion(), consultorioDto.getDireccion(), consultorioDto.getNumero());

            Consultorio consultorioDb = new Consultorio();
            consultorioDb.setCiudad(consultorioDto.getCiudad());
            consultorioDb.setDireccion(consultorioDto.getDireccion());
            consultorioDb.setNumero(consultorioDto.getNumero());
            consultorioDb.setDescripcion(consultorioDto.getDescripcion());

            if (consultorioByDireccion.size() > 0) {
                for (Consultorio consultorio : consultorioByDireccion) {
                    if (consultorio.equals(consultorioDb)) {
                        throw new ConsultorioAlreadyExistException("Error el consultorio a ingresar ya existe");
                    }
                }
            } else {
                consultorioDb = consultorioRepository.save(consultorioDb);
                consultorioDto = mapper.convertToDto(consultorioDb, ConsultorioDto.class);

            }
        } catch (RuntimeException ex) {
            throw new ConsultorioDoesntExistException("Error al ingresar el consultorio " + ex);
        }
        return consultorioDto;

    }

    @Override
    public ConsultorioDto updateConsultorio(ConsultorioDto consultorioDto) {
        Optional<Consultorio> consultorioOp = consultorioRepository.findById(consultorioDto.getId());
        if (consultorioOp.isEmpty()) {
            throw new ConsultorioDoesntExistException("El consultorio a modificar no existe");
        }
        // ahora consultar si los datos para insertar ya existen para que no se repitan.
        Consultorio consultorioDb = consultorioOp.get();
        consultorioDb.setCiudad(consultorioDto.getCiudad());
        consultorioDb.setDireccion(consultorioDto.getDireccion());
        consultorioDb.setNumero(consultorioDto.getNumero());
        consultorioDb.setDescripcion(consultorioDto.getDescripcion());

        List<Consultorio> consultorioByDireccion = consultorioRepository
                .findByCiudadAndDescripcionAndDireccionAndNumero(consultorioDto.getCiudad(),
                        consultorioDto.getDescripcion(), consultorioDto.getDireccion(), consultorioDto.getNumero());
        if (consultorioByDireccion.size() > 0) {
            for (Consultorio consultorio : consultorioByDireccion) {
                if (consultorio.equals(consultorioDb)) {
                    throw new ConsultorioAlreadyExistException(
                            "Error, el consultorio cuenta ya con los mismos datos registrados");
                }
            }
        } else {
            consultorioDb = consultorioRepository.save(consultorioDb);
        }
        return mapper.convertToDto(consultorioDb, ConsultorioDto.class);

    }

    @Override
    public void deleteConsultorio(Long id) {
        Optional<Consultorio> consultorioOp = consultorioRepository.findById(id);
        if (consultorioOp.isEmpty()) {
            throw new ConsultorioDoesntExistException("El consultorio no existe");
        }
        Consultorio consultorioDb = consultorioOp.get();
        consultorioRepository.delete(consultorioDb);
    }

    @Override
    public ConsultorioDto getConsultorioById(Long id) {
        Optional<Consultorio> consultorioOptional = consultorioRepository.findById(id);
        if (consultorioOptional.isEmpty()) {
            throw new ConsultorioDoesntExistException("El consultorio no existe");
        }
        Consultorio consultorioDb = consultorioOptional.get();
        return mapper.convertToDto(consultorioDb, ConsultorioDto.class);
    }

    @Override
    public List<ConsultorioDto> getConsultoriosDisponibles(String fechaInicioStr, String fechaFinStr) {
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy
        // HH:mm");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime fechaInicio;
        LocalDateTime fechaFin;
        try {
            // conviertiendo a formato localdatetime del repository, se hizo aca porque no
            // tengo nungun DTO para hacer esa
            // conversion directa desde el dto
            fechaInicio = LocalDateTime.parse(fechaInicioStr, formatter);
            fechaFin = LocalDateTime.parse(fechaFinStr, formatter);
            if ((fechaInicio.isBefore(currentDate)) || (fechaInicio.isAfter(fechaFin))) {
                throw new ConsultorioAlreadyExistException(
                        "La fecha de inicio a reservar no pueden ser inferior a la fecha de hoy, o a la fecha de fin reserva");
            } else if (fechaFin.isBefore(currentDate)) {
                throw new ConsultorioAlreadyExistException(
                        "Las fechas de fin a reservar no pueden ser anterior a la fecha de hoy");
            }

            List<Consultorio> consultoriosLibres = consultorioRepository.obtenerConsultoriosDisponibles(fechaInicio,
                    fechaFin);

            return consultoriosLibres.stream()
                    .map(consultorio -> mapper.convertToDto(consultorio, ConsultorioDto.class))
                    .collect(Collectors.toList());
        } catch (DateTimeParseException e) {
            // Manejo de error cuando el formato de fecha es incorrecto
            // Puedes devolver un mensaje de error o lanzar una excepción personalizada, por
            // ejemplo:
            throw new IllegalArgumentException("Formato de fecha inválido. Utilice el formato dd-MM-yyyy HH:mm"
                    + "fechaInicio " + fechaInicioStr + "fechaFin" + fechaFinStr);
        }

    }

}
