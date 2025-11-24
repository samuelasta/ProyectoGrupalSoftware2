package co.edu.uniquindio.ProyectoGrupal.Excepciones;
/**
 * Excepción lanzada cuando se intenta realizar una operación que depende de la existencia
 * de un usuario específico, pero este no se encuentra registrado en el sistema.
 * * Esta excepción es crucial para el manejo de errores de recursos no encontrados
 * en peticiones que incluyen el ID de un usuario (ej. consultar historial, registrar reserva).
 * * Extiende {@code RuntimeException}, lo que la clasifica como una excepción no verificada (unchecked).
 * * Cuando es atrapada por un controlador REST, debe resultar en un código de estado
 * HTTP 404 (Not Found).
 *
 * @author Danel Eduardo Jurado Celemin
 * @version 1.0
 */
public class UsuarioNoEncontradoException extends RuntimeException{
    /**
     * Constructor por defecto.
     */
    public UsuarioNoEncontradoException() {
        super();
    }
    /**
     * Constructor que acepta un mensaje detallado sobre la causa de la excepción.
     *
     * @param message El mensaje descriptivo que indica qué usuario o ID causó la excepción.
     */
    public UsuarioNoEncontradoException(String message) {
        super(message);
    }
    /**
     * Constructor que acepta un mensaje detallado y la causa raíz de la excepción.
     * * Útil para envolver (wrap) otras excepciones de nivel inferior (ej. SQLException).
     *
     * @param message El mensaje descriptivo de la excepción.
     * @param cause La excepción original que causó este problema.
     */
    public UsuarioNoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}
