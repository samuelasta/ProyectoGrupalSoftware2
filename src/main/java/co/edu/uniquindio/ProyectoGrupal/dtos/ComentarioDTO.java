package co.edu.uniquindio.ProyectoGrupal.dtos;
/**
 * Data Transfer Object (DTO) utilizado para representar la información de un comentario
 * que se envía o se recibe desde la capa de presentación (controlador) hacia el cliente.
 * * Dado que es un record de Java (introducido en Java 16), es inmutable y conciso,
 * siendo ideal para la transferencia de datos.
 *
 * @param id El identificador único del comentario.
 * @param comentario El texto o contenido del comentario realizado por el usuario.
 * @param calificacion La puntuación o valoración numérica (ej., de 1 a 5) asociada al comentario.
 * @param usuarioNombre El nombre legible del usuario que realizó el comentario. Se incluye
 * para facilitar la presentación de la información sin tener que consultar la entidad de usuario.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 */
public record ComentarioDTO(String id,
                            String comentario,
                            int calificacion,
                            String usuarioNombre) {
}
