package co.edu.uniquindio.ProyectoGrupal.dtos;
/**
 * Data Transfer Object (DTO) utilizado para encapsular la información de una opinión
 * que es devuelta al cliente después de un registro o consulta exitosa.
 * * Este DTO se diferencia del {@link OpinionRequestDTO} al incluir campos de identificación
 * amigables (como nombres y títulos) y el ID generado de la opinión.
 * * Al ser un record, garantiza la inmutabilidad de los datos de salida.
 *
 * @param id El identificador único generado para la opinión que fue registrada.
 * @param usuarioNombre El nombre legible del usuario que emitió la opinión.
 * @param alojamientoTitulo El título o nombre del alojamiento que ha sido calificado,
 * facilitando su visualización en la interfaz de usuario.
 * @param calificacion La puntuación numérica otorgada (ej., de 1 a 5).
 * @param comentario El texto completo de la opinión o reseña.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 */
public record OpinionResponseDTO( String id,
                                  String usuarioNombre,
                                  String alojamientoTitulo,
                                  int calificacion,
                                  String comentario) {
}
