package co.edu.uniquindio.ProyectoGrupal.service;

import co.edu.uniquindio.ProyectoGrupal.dtos.ReservaDTO;
/**
 * Interfaz de servicio que define el contrato de negocio para las operaciones
 * relacionadas con la gestión y el ciclo de vida de las reservas de alojamiento.
 * * Las implementaciones de esta interfaz (ej. {@code ReservaServiceImpl}) deben contener
 * la lógica de validación necesaria para modificar el estado de una reserva.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 */
public interface ReservaService {
    /**
     * Procesa la solicitud de cancelación de una reserva específica.
     * * Este método aplica las reglas de negocio para determinar si una reserva puede ser cancelada,
     * actualiza su estado a CANCELADA en la persistencia y retorna el nuevo estado.
     *
     * @param idReserva El identificador único de la reserva que se desea cancelar.
     * @return El {@link co.edu.uniquindio.ProyectoGrupal.dtos.ReservaDTO} que contiene el ID
     * de la reserva y su nuevo estado (CANCELADA) si la operación fue exitosa.
     * @throws co.edu.uniquindio.ProyectoGrupal.Excepciones.ReservaNoEncontradaException Si la reserva con el ID dado no existe.
     * @throws co.edu.uniquindio.ProyectoGrupal.Excepciones.ReservaNoCancelableException Si la reserva existe, pero no cumple con los criterios de cancelación.
     * @see co.edu.uniquindio.ProyectoGrupal.service.impl.ReservaServiceImpl
     */
    ReservaDTO cancelarReserva(String idReserva);
}
