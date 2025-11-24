package co.edu.uniquindio.ProyectoGrupal.dtos;

import co.edu.uniquindio.ProyectoGrupal.model.enums.EstadoReserva;
import java.time.LocalDateTime;
/**
 * Data Transfer Object (DTO) utilizado para intercambiar información detallada de una
 * notificación de reserva entre las capas de la aplicación.
 * * Este DTO encapsula tanto los detalles de la reserva como la información esencial
 * del usuario para su visualización o procesamiento.
 * * Dado que es un record, sus campos son inmutables.
 *
 * @param id El identificador único de la notificación.
 * @param checkIn La fecha y hora de inicio de la reserva (check-in).
 * @param checkOut La fecha y hora de finalización de la reserva (check-out).
 * @param numeroHuespedes El número total de huéspedes incluidos en la reserva.
 * @param estado El estado actual de la reserva asociada (ej. PENDIENTE, CONFIRMADA, CANCELADA),
 * utilizando el enumerado {@link co.edu.uniquindio.ProyectoGrupal.model.enums.EstadoReserva}.
 * @param fechaCreacion La marca de tiempo que indica cuándo se generó esta notificación.
 * @param usuarioId El identificador único del usuario que realizó la reserva.
 * @param usuarioNombre El nombre completo del usuario que realizó la reserva, incluido para
 * simplificar la presentación de la notificación.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 */
public record NotificacionDTO(
        String id,
        LocalDateTime checkIn,
        LocalDateTime checkOut,
        int numeroHuespedes,
        EstadoReserva estado,
        LocalDateTime fechaCreacion,
        String usuarioId,
        String usuarioNombre
) {}
