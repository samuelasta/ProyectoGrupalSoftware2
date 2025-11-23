package co.edu.uniquindio.ProyectoGrupal.Excepciones;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
