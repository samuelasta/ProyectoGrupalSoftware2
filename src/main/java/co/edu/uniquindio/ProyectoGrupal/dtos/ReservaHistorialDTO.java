package co.edu.uniquindio.ProyectoGrupal.dtos;

import co.edu.uniquindio.ProyectoGrupal.model.enums.EstadoReserva;

import java.time.LocalDateTime;
/**
 * Data Transfer Object (DTO) utilizado para encapsular la información esencial de una reserva
 * que se presenta en el historial de un usuario.
 * * Este DTO se utiliza típicamente para transferir datos desde la capa de servicio al
 * {@link co.edu.uniquindio.ProyectoGrupal.controller.HistorialController}.
 * * Se enfoca en los datos clave de tiempo, estado y capacidad de la reserva.
 * * Al ser un record, garantiza la inmutabilidad de los datos de historial.
 *
 * @param id El identificador único de la reserva.
 * @param checkIn La fecha y hora programada para el inicio de la reserva.
 * @param checkOut La fecha y hora programada para la finalización de la reserva.
 * @param numeroHuespedes La cantidad de personas registradas para esta reserva.
 * @param estado El estado actual de la reserva (ej. CONFIRMADA, CANCELADA, FINALIZADA),
 * utilizando el enumerado {@link co.edu.uniquindio.ProyectoGrupal.model.enums.EstadoReserva}.
 * @param fechaCreacion La marca de tiempo que indica cuándo se creó inicialmente la reserva.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 */
public record ReservaHistorialDTO(String id,
                                  LocalDateTime checkIn,
                                  LocalDateTime checkOut,
                                  int numeroHuespedes,
                                  EstadoReserva estado,
                                  LocalDateTime fechaCreacion) {
}
