package com.citasmedicas.citasmedicas.service.imp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citasmedicas.citasmedicas.controller.dto.ConsultorioAsignadoRequestDto;
import com.citasmedicas.citasmedicas.controller.dto.ConsultorioAsignadoResponseDto;
import com.citasmedicas.citasmedicas.controller.dto.ConsultorioDto;
import com.citasmedicas.citasmedicas.controller.dto.DoctorResponseDto;
import com.citasmedicas.citasmedicas.controller.dto.EspecialidadDto;

import com.citasmedicas.citasmedicas.exceptions.ConsultorioAlreadyExistException;
import com.citasmedicas.citasmedicas.exceptions.ConsultorioDoesntExistException;
import com.citasmedicas.citasmedicas.exceptions.ConsultorioReservadoAlreadyExistException;
import com.citasmedicas.citasmedicas.exceptions.DoctorDoesntExistExceptions;
import com.citasmedicas.citasmedicas.exceptions.EspecialidadDoesntExistExceptions;

import com.citasmedicas.citasmedicas.model.entity.Consultorio;
import com.citasmedicas.citasmedicas.model.entity.ConsultorioAsignado;
import com.citasmedicas.citasmedicas.model.entity.Doctor;
import com.citasmedicas.citasmedicas.model.entity.EnumEspecialidad;
import com.citasmedicas.citasmedicas.model.entity.Especialidad;

import com.citasmedicas.citasmedicas.model.repository.ConsultorioAsignadoRepository;

import com.citasmedicas.citasmedicas.service.ConsultorioAsignadoService;
import com.citasmedicas.citasmedicas.service.ConsultorioService;
import com.citasmedicas.citasmedicas.service.DoctorService;
import com.citasmedicas.citasmedicas.service.EspecialidadesService;
import com.citasmedicas.citasmedicas.util.CommonMapper;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@Service
public class ConsultorioAsignadoServiceImpl implements ConsultorioAsignadoService {
    // private static final Logger logger =
    // LoggerFactory.getLogger(ConsultorioAsignadoServiceImpl.class);

    private final ConsultorioAsignadoRepository consultorioAsignadoRepository;
    @Autowired
    private ConsultorioService consultorioService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private EspecialidadesService especialidadService;
    private static final LocalDateTime currentDate = LocalDateTime.now();
    private final CommonMapper mapper;

    public ConsultorioAsignadoServiceImpl(ConsultorioAsignadoRepository consultorioAsignadoRepository,
            CommonMapper mapper) {
        this.consultorioAsignadoRepository = consultorioAsignadoRepository;
        this.mapper = mapper;

    }

    @Override //OK DTO
    public List<ConsultorioAsignadoResponseDto> getConsultoriosAsignados() {
        try {
            List<ConsultorioAsignado> consultorioAsignados = consultorioAsignadoRepository.findAll();
            return consultorioAsignados.stream().map(
                consAsign -> mapper.transformarConsultAsigADto(consAsign))
                    .collect(Collectors.toList());
        } catch (RuntimeException ex) {
            throw new UnsupportedOperationException("Unimplemented method 'getConsultoriosAsignados'" + ex);
        }
    }

    @Override //PDT DTO
    public ConsultorioAsignadoResponseDto createConsultorioAsignado(
            ConsultorioAsignadoRequestDto consultorioAsignadoRequestDto) {
        try {

            if ((consultorioAsignadoRequestDto.getInicioReserva().isBefore(currentDate))
                    || (consultorioAsignadoRequestDto.getInicioReserva()
                            .isAfter(consultorioAsignadoRequestDto.getFinReserva()))) {
                throw new ConsultorioAlreadyExistException(
                        "La fecha de inicio a reservar no pueden ser inferior a la fecha de hoy o a la fecha de fin reserva");
            } else if (consultorioAsignadoRequestDto.getFinReserva()
                    .isBefore(consultorioAsignadoRequestDto.getInicioReserva())) {
                throw new ConsultorioAlreadyExistException(
                        "Las fechas de fin a reservar no pueden ser anterior a la fecha de inicio");
            }
            // BUSCA LOS CONSULTORIOS QUE YA TIENEN ASIGNADO ALGO EN Esa FECHA
            List<ConsultorioAsignado> consultAsig = consultorioAsignadoRepository
                    .findAllByInicioReservaLessThanEqualAndFinReservaGreaterThanEqual(
                            consultorioAsignadoRequestDto.getInicioReserva(),
                            consultorioAsignadoRequestDto.getFinReserva());

            // MIRAR SI EL ID DEL CONSULTORIO A RESERVAR ES EXACTAMENTE EL MISMO QUE LLEGA
            // DEL REQUEST
            if (consultAsig.size() > 0) {
                for (ConsultorioAsignado consultorioReservado : consultAsig) {
                    if (consultorioReservado.getConsultorio().getId()
                            .equals(consultorioAsignadoRequestDto.getId_consultorio())) {
                        throw new ConsultorioReservadoAlreadyExistException(
                                "No se puede reservar, ya existe una reserva en ese rango de fechas para el consultorio seleccionado Consultorio : "
                                        + consultorioReservado.getConsultorio().getId() + " ]");
                    }
                }

            }
            ConsultorioDto consultDto = consultorioService
                    .getConsultorioById(consultorioAsignadoRequestDto.getId_consultorio());

            DoctorResponseDto doctorDto = doctorService.getDoctoresById(consultorioAsignadoRequestDto.getId_doctor());
            
            EspecialidadDto especialidadDto = especialidadService
                    .getEspecialidadByNombre(doctorDto.getNombreEspecialidad());

            ConsultorioAsignado consultAsigDb = new ConsultorioAsignado();
            consultAsigDb.setInicioReserva(consultorioAsignadoRequestDto.getInicioReserva());
            consultAsigDb.setFinReserva(consultorioAsignadoRequestDto.getFinReserva());
            
            //CONVIRTIENDO DTO validar si se puede hacer de mejor forma
            consultAsigDb.setConsultorio(mapper.convertToEntity(consultDto, Consultorio.class));
           /* consultAsigDb.setConsultorio(new Consultorio(consultDto.getId(), consultDto.getCiudad(),
                    consultDto.getDireccion(), consultDto.getNumero(), consultDto.getDescripcion()));
                     */

            /* Asignando DOCTOR, */
            
            Especialidad especialidadAsigDb = new Especialidad(especialidadDto.getId(),
                    EnumEspecialidad.valueOf(especialidadDto.getNombre()));

            Doctor doctorAsigDb = new Doctor(doctorDto.getId(), doctorDto.getNombre(), doctorDto.getApellido(),
                    doctorDto.getCorreo(), especialidadAsigDb);

            consultAsigDb.setDoctor(doctorAsigDb);
            consultAsigDb = consultorioAsignadoRepository.save(consultAsigDb);

            return mapper.transformarConsultAsigADto(consultAsigDb);

        } catch (ConsultorioDoesntExistException ex) {
            throw new ConsultorioDoesntExistException("El consultorio ingresado no existe");
        } catch (DoctorDoesntExistExceptions ex) {
            throw new DoctorDoesntExistExceptions("El doctor ingresado no existe");
        } catch (ConsultorioReservadoAlreadyExistException ex) {
            throw new ConsultorioAlreadyExistException("Error " + ex);
        }
    }

    @Override //OK DTO
    public List<ConsultorioAsignadoResponseDto> getConsultorioAsignadoByConsultorio(Long id) {
        try {
            // buscar si el id del consultorio existe
            ConsultorioDto consultorioOp = consultorioService.getConsultorioById(id);

            List<ConsultorioAsignado> consultorioAsignados = consultorioAsignadoRepository
                    .findAllByConsultorioId(consultorioOp.getId());
            return consultorioAsignados.stream().map(
                    consAsign -> mapper.transformarConsultAsigADto(consAsign))
                    .collect(Collectors.toList());
        } catch (ConsultorioDoesntExistException ex) {
            throw new ConsultorioDoesntExistException("El consultorio ingresado no existe");
        } catch (RuntimeException ex) {
            throw new UnsupportedOperationException("ERROR EN CONSULTORIO ASIGNADO'" + ex);
        }
    }

    @Override //OK DTO
    public List<ConsultorioAsignadoResponseDto> getConsultorioAsignadoByEspecialidad(String especialidad) {
        try {

            // trae uunicamente los consuktoriosAsingados que tengan esa especialidad
            EspecialidadDto especialidadOp = especialidadService.getEspecialidadByNombre(especialidad.toUpperCase());
            List<ConsultorioAsignado> consultorioAsignados = consultorioAsignadoRepository
            .findAllByDoctorEspecialidad(mapper.transformarDtoAEspecialidad(especialidadOp));
                       
            return consultorioAsignados.stream().map(
                    consAsign -> mapper.transformarConsultAsigADto(consAsign))
                    .collect(Collectors.toList());

        } catch (EspecialidadDoesntExistExceptions ex) {
            throw new EspecialidadDoesntExistExceptions("La especialidad no existe ");
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error al consultar por especialidad" + especialidad + " " + ex);
        }

    }

}
