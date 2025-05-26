package Pet.Society.models.exceptions;

public class AppointmentAlreadyScheduledException extends RuntimeException {
    public AppointmentAlreadyScheduledException(String message) {
        super(message);
    }
}
