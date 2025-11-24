package co.edu.uniquindio.ProyectoGrupal.Excepciones;
/**
 * Excepción lanzada cuando se intenta realizar una operación (como consultar, modificar o cancelar)
 * sobre una reserva utilizando un identificador (ID) que no corresponde a ninguna reserva
 * existente en el sistema.
 * * Esta es una excepción de negocio que indica la ausencia de un recurso específico,
 * siendo apropiada para retornar un código de estado HTTP 404 (Not Found) en la capa de controlador.
 * * Extiende {@code RuntimeException}, lo que la clasifica como una excepción no verificada (unchecked).
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 */
public class ReservaNoEncontradaException extends RuntimeException{
    /**
     * Constructor que acepta un mensaje detallado sobre la causa de la excepción.
     *
     * @param mensaje El mensaje descriptivo que indica qué reserva o ID causó la excepción (ej. "No existe reserva con el ID especificado.").
     */
    public ReservaNoEncontradaException(String mensaje) { super(mensaje); }

}
