package co.edu.uniquindio.ProyectoGrupal.service;

import co.edu.uniquindio.ProyectoGrupal.dtos.OpinionRequestDTO;
import co.edu.uniquindio.ProyectoGrupal.dtos.OpinionResponseDTO;
/**
 * Interfaz de servicio que define el contrato de negocio para el registro y gestión
 * de opiniones (comentarios y calificaciones) de los usuarios sobre los alojamientos.
 * * Las implementaciones de esta interfaz (ej. {@code OpinionServiceImpl}) son responsables
 * de validar la existencia de los recursos referenciados (Usuario, Alojamiento, Reserva)
 * y de persistir la nueva opinión.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 */
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
