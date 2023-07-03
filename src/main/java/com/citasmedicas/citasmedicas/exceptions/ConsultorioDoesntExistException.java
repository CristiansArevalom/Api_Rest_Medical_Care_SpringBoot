package com.citasmedicas.citasmedicas.exceptions;

public class ConsultorioDoesntExistException extends RuntimeException {
    
    public ConsultorioDoesntExistException(String mensaje){
        super(mensaje);
    }
}
