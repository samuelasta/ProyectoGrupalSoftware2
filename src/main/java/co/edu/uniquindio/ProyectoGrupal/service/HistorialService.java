package co.edu.uniquindio.ProyectoGrupal.service;

import co.edu.uniquindio.ProyectoGrupal.dtos.ReservaHistorialDTO;

import java.util.List;
/**
 * Interfaz de servicio que define el contrato de negocio para la obtención del historial
 * completo de reservas de un usuario.
 * * Las implementaciones de esta interfaz (ej. {@code HistorialServiceImpl}) se encargan de
 * acceder a los datos de reservas, filtrarlos por usuario y prepararlos para la presentación.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 */
public interface HistorialService {
    /**
     * Recupera todas las reservas asociadas a un usuario específico,
     * incluyendo reservas activas, pasadas y canceladas.
     *
     * @param idUsuario El identificador único del usuario cuyo historial se desea consultar.
     * @return Una {@code List} de {@link co.edu.uniquindio.ProyectoGrupal.dtos.ReservaHistorialDTO}.
     * Retorna una lista vacía si el usuario no tiene reservas, o si el usuario no existe
     * (dependiendo de la lógica de manejo de excepciones de la implementación).
     * @throws co.edu.uniquindio.ProyectoGrupal.Excepciones.UsuarioNoEncontradoException Si el usuario no existe.
     * (Esta excepción es típicamente lanzada por la implementación).
     */
    List<ReservaHistorialDTO> obtenerReservasUsuario(String idUsuario);
}
