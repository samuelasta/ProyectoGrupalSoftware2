package co.edu.uniquindio.ProyectoGrupal.model.enums;
/**
 * Enumerado que define los posibles estados por los que puede pasar una reserva
 * de alojamiento a lo largo de su ciclo de vida en el sistema.
 * * Este enumerado es utilizado para controlar la lógica de negocio, como la
 * cancelación o la consulta del historial.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 */
public enum EstadoReserva {
    /**
     * La reserva está actualmente en curso, lo que indica que el huésped
     * ha realizado el check-in.
     */
    ACTIVA,
    /**
     * La reserva ha sido creada, pero aún está pendiente de confirmación de pago
     * o de ser validada antes de pasar al estado ACTIVA.
     */
    PENDIENTE,
    /**
     * La reserva ha sido anulada por el huésped o por el administrador antes
     * de la fecha de check-in.
     */
    CANCELADA,
    /**
     * La reserva ha concluido, lo que indica que el huésped ha realizado el
     * check-out y la estancia ha finalizado.
     */
    FINALIZADA
}
