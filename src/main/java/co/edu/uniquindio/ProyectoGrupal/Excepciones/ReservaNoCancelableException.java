package co.edu.uniquindio.ProyectoGrupal.Excepciones;

public class ReservaNoCancelableException extends RuntimeException{
    public ReservaNoCancelableException(String mensaje) { super(mensaje); }
}
