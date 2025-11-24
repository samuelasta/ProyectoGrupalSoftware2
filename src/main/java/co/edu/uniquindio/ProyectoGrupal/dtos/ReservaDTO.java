package co.edu.uniquindio.ProyectoGrupal.dtos;

import co.edu.uniquindio.ProyectoGrupal.model.enums.EstadoReserva;
/**
 * Data Transfer Object (DTO) minimalista utilizado para comunicar la identificación
 * y el estado actual de una reserva.
 * * Este DTO es especialmente útil en operaciones de actualización, como la cancelación,
 * donde solo se necesita devolver el ID y el nuevo estado de la reserva al cliente.
 * * Al ser un record, garantiza la inmutabilidad de sus componentes.
 *
 * @param id El identificador único de la reserva.
 * @param estado El estado actual de la reserva, utilizando el enumerado
 * {@link co.edu.uniquindio.ProyectoGrupal.model.enums.EstadoReserva} (ej. CONFIRMADA, CANCELADA).
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 */
public record ReservaDTO(String id,
                         EstadoReserva estado) {
}
