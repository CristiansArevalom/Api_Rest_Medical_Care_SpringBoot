package com.citasmedicas.citasmedicas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class EspecialidadDoesntExistAdvice {
        /*advice, el cual captura la excepción y ofrece una vista adecuada.
Dentro de dicho advice se suelen utilizar herramientas como los códigos de estados de las peticiones HTTP,
y los mensajes personalizados que especifican la causa del error. */

    @ResponseBody //Esta anotación se utiliza para indicar que el método de controlador de Spring debe devolver el cuerpo de la respuesta HTTP como el resultado de la llamada
    @ExceptionHandler(EspecialidadDoesntExistExceptions.class)//La anotación "@ExceptionHandler" se utiliza en un método de un controlador de Spring para manejar excepciones específicas que pueden ser lanzadas durante la ejecución del método. Cuando se lanza una excepción, Spring buscará un método anotado con "@ExceptionHandler" que coincida con el tipo de excepción lanzada y lo invocará para manejar la excepción.    
    @ResponseStatus(HttpStatus.CONFLICT)
    String EspecialidadAdvice(EspecialidadDoesntExistExceptions ex){
        return ex.getMessage();
    }
}
