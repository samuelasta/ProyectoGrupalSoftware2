package co.edu.uniquindio.ProyectoGrupal.Excepciones;
/**
 * Excepción lanzada cuando se intenta cancelar una reserva, pero esta no cumple con las
 * reglas de negocio o las condiciones necesarias para ser elegible para la cancelación.
 * * Este es un tipo de excepción de negocio que indica que la solicitud es válida a nivel
 * sintáctico, pero no puede ser procesada por la lógica de la aplicación.
 * * Extiende {@code RuntimeException}, lo que permite que sea una excepción no verificada (unchecked).
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 */
public class ReservaNoCancelableException extends RuntimeException{
    /**
     * Constructor que acepta un mensaje detallado explicando la razón por la cual la reserva
     * específica no pudo ser cancelada (ej. "La fecha de cancelación permitida ha expirado.").
     *
     * @param mensaje El mensaje descriptivo de la causa de la no cancelación.
     */
    public ReservaNoCancelableException(String mensaje) { super(mensaje); }
}
