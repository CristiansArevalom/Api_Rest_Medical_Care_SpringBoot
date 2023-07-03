package com.citasmedicas.citasmedicas.exceptions;

public class ConsultorioReservadoAlreadyExistException extends RuntimeException {
    
    public ConsultorioReservadoAlreadyExistException(String mensaje){
        super(mensaje);
    }
}
