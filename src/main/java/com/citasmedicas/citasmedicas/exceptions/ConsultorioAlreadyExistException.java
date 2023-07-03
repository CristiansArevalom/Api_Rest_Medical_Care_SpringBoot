package com.citasmedicas.citasmedicas.exceptions;

public class ConsultorioAlreadyExistException extends RuntimeException {
    
    public ConsultorioAlreadyExistException(String mensaje){
        super(mensaje);
    }
}
