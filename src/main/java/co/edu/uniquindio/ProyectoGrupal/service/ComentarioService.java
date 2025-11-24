package co.edu.uniquindio.ProyectoGrupal.service;

import co.edu.uniquindio.ProyectoGrupal.dtos.ComentarioDTO;

import java.util.List;

public interface ComentarioService {
    /**
     * Obtiene todos los comentarios de un alojamiento.
     *
     * @param idAlojamiento id del alojamiento.
     * @return Lista de ComentarioDTO.
     */
    List<ComentarioDTO> obtenerComentariosPorAlojamiento(String idAlojamiento);
}
