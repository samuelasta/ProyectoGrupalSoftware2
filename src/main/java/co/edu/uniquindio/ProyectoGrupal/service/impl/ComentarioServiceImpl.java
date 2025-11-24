package co.edu.uniquindio.ProyectoGrupal.service.impl;

import co.edu.uniquindio.ProyectoGrupal.dtos.ComentarioDTO;
import co.edu.uniquindio.ProyectoGrupal.mapper.ComentarioMapper;
import co.edu.uniquindio.ProyectoGrupal.model.Comentario;
import co.edu.uniquindio.ProyectoGrupal.repository.ComentarioRepository;
import co.edu.uniquindio.ProyectoGrupal.service.ComentarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
/**
 * Implementación de la interfaz {@link co.edu.uniquindio.ProyectoGrupal.service.ComentarioService}.
 * * Contiene la lógica de negocio para la gestión de comentarios, delegando la persistencia
 * a {@link co.edu.uniquindio.ProyectoGrupal.repository.ComentarioRepository} y la transformación de datos
 * a {@link co.edu.uniquindio.ProyectoGrupal.mapper.ComentarioMapper}.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 */
@Service
public class ComentarioServiceImpl implements ComentarioService {

    private final ComentarioRepository comentarioRepo;
    private final ComentarioMapper comentarioMapper;
    /**
     * Constructor para la inyección de dependencias.
     * * Spring inyecta automáticamente el repositorio y el mapper necesarios.
     *
     * @param comentarioRepo El repositorio de comentarios para el acceso a la base de datos.
     * @param comentarioMapper El mapper para la conversión entre entidad y DTO.
     */
    public ComentarioServiceImpl(ComentarioRepository comentarioRepo, ComentarioMapper comentarioMapper) {
        this.comentarioRepo = comentarioRepo;
        this.comentarioMapper = comentarioMapper;
    }
    /**
     * Recupera todos los comentarios asociados a un alojamiento específico.
     * * El proceso implica consultar el repositorio por el ID del alojamiento y luego
     * transformar la lista de entidades {@code Comentario} a una lista de DTOs.
     *
     * @param idAlojamiento El ID único del alojamiento.
     * @return Una lista de {@link co.edu.uniquindio.ProyectoGrupal.dtos.ComentarioDTO}.
     * Retorna una lista vacía si no hay comentarios para el alojamiento dado.
     * @see co.edu.uniquindio.ProyectoGrupal.repository.ComentarioRepository#findByAlojamiento_Id(String)
     */
    @Override
    public List<ComentarioDTO> obtenerComentariosPorAlojamiento(String idAlojamiento) {
        // 1. Consulta al repositorio usando el método de Spring Data JPA.
        List<Comentario> comentarios = comentarioRepo.findByAlojamiento_Id(idAlojamiento);
        // 2. Transforma la lista de entidades a una lista de DTOs usando Stream API y el mapper.
        return comentarios.stream()
                .map(comentarioMapper::toDTO)
                .collect(Collectors.toList());
    }
}