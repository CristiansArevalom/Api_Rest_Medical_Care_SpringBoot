package com.citasmedicas.citasmedicas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class ConsultorioDoesntExistAdvice {
        /*advice, el cual captura la excepción y ofrece una vista adecuada.
        Dentro de dicho advice se suelen utilizar herramientas como los códigos de estados de las peticiones HTTP,
        y los mensajes personalizados que especifican la causa del error. */
        @ResponseBody
        @ExceptionHandler(ConsultorioDoesntExistException.class)
        @ResponseStatus(HttpStatus.CONFLICT)
        String ConsultorioAdvice(ConsultorioDoesntExistException ex){
            return ex.getMessage();
        }
}
