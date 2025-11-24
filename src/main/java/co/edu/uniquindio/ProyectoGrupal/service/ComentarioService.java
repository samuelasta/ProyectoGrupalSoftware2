package co.edu.uniquindio.ProyectoGrupal.service;

import co.edu.uniquindio.ProyectoGrupal.dtos.ComentarioDTO;

import java.util.List;
/**
 * Interfaz de servicio que define el contrato de negocio para las operaciones
 * relacionadas con los comentarios (opiniones y valoraciones) en el sistema.
 * * Las implementaciones de esta interfaz deben manejar la lógica de negocio,
 * como la recuperación y potencial filtrado o validación de comentarios.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 */
public interface ComentarioService {
    /**
     * Obtiene todos los comentarios de un alojamiento.
     *
     * @param idAlojamiento id del alojamiento.
     * @return Lista de ComentarioDTO.
     */
    List<ComentarioDTO> obtenerComentariosPorAlojamiento(String idAlojamiento);
}
