package co.edu.uniquindio.ProyectoGrupal.dtos;
/**
 * Data Transfer Object (DTO) utilizado para encapsular los datos requeridos en la petición
 * de registro de una nueva opinión o valoración sobre un alojamiento.
 * * Este DTO se utiliza típicamente como el cuerpo (Request Body) en una petición POST.
 * * Al ser un record, garantiza la inmutabilidad de los datos de entrada.
 *
 * @param usuarioId El identificador único del usuario que está emitiendo la opinión.
 * Es necesario para vincular la opinión al autor.
 * @param alojamientoId El identificador único del alojamiento que está siendo calificado.
 * Necesario para saber a qué recurso pertenece la opinión.
 * @param reservaId Opcional. El identificador único de la reserva específica asociada a esta opinión,
 * si la regla de negocio exige que solo se pueda opinar sobre reservas completadas.
 * @param calificacion La puntuación numérica otorgada al alojamiento (ej., de 1 a 5).
 * @param comentario El texto detallado de la opinión o reseña.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 */
public record OpinionRequestDTO( String usuarioId,
                                 String alojamientoId,
                                 String reservaId,
                                 int calificacion,
                                 String comentario) {
}
