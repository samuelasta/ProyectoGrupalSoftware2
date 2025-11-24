package co.edu.uniquindio.ProyectoGrupal.service;

import co.edu.uniquindio.ProyectoGrupal.dtos.OpinionRequestDTO;
import co.edu.uniquindio.ProyectoGrupal.dtos.OpinionResponseDTO;

public interface OpinionService {
    /**
     * Registra una opinión (comentario y calificación) de un usuario sobre un alojamiento,
     * asociada a una reserva.
     *
     * @param request DTO con usuarioId, alojamientoId, reservaId, calificación y comentario
     * @return DTO con la información registrada de la opinión
     */
    OpinionResponseDTO registrarOpinion(OpinionRequestDTO request);
}
