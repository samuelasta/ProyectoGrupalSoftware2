package co.edu.uniquindio.ProyectoGrupal.Excepciones;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
