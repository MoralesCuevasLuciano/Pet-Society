package Pet.Society.models.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerException {

    //CLASE CREADA PARA QUE AL SALIR ESTAS EXCEPCIONES, APAREZCA LA PETICIÓN CORRESPONDIENTE.

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<String> HandlerUserExistsException(UserExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("The user already exists");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> HandlerUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user does not exist");
    }

    @ExceptionHandler(UnsubscribedUserException.class)
    public ResponseEntity<String> HandlerUnsubscribedUserException(UnsubscribedUserException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("The user is unsubscribed");
    }

    @ExceptionHandler(UserAttributeException.class)
    public ResponseEntity<String> handleUserAttributeException(UserAttributeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The data entered is not correct");
    }

}
