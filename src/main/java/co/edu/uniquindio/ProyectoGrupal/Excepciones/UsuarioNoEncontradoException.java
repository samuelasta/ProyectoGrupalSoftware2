package co.edu.uniquindio.ProyectoGrupal.Excepciones;

public class UsuarioNoEncontradoException extends RuntimeException{
    public UsuarioNoEncontradoException() {
        super();
    }

    public UsuarioNoEncontradoException(String message) {
        super(message);
    }

    public UsuarioNoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}
