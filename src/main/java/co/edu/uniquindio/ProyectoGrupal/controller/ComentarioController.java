package co.edu.uniquindio.ProyectoGrupal.controller;


import co.edu.uniquindio.ProyectoGrupal.dtos.ComentarioDTO;
import co.edu.uniquindio.ProyectoGrupal.service.ComentarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controlador REST para gestionar las operaciones relacionadas con los comentarios.
 * * Expone endpoints HTTP para la consulta y potencial manipulación de comentarios
 * sobre los alojamientos, delegando la lógica de negocio a {@link ComentarioService}.
 * * La ruta base para este controlador es {@code /comentarios}.
 * * @author Daniel Eduardo Jurado Celeimn
 * @version 1.0
 */
@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;
    /**
     * Constructor para la inyección de dependencias.
     * * Inyecta la implementación de {@link ComentarioService} necesaria para
     * acceder a la lógica de negocio de comentarios.
     *
     * @param comentarioService El servicio de comentarios inyectado por Spring.
     */
    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }
    /**
     * Maneja la petición GET para obtener todos los comentarios asociados a un alojamiento específico.
     * * La ruta es {@code GET /comentarios/alojamiento/{idAlojamiento}}.
     *
     * @param idAlojamiento El ID único del alojamiento cuyos comentarios se desean consultar.
     * Se extrae como una variable de ruta (Path Variable).
     * @return {@code ResponseEntity<List<ComentarioDTO>>} La respuesta HTTP que contiene:
     * <ul>
     * <li>Código de estado 200 (OK) si la consulta es exitosa.</li>
     * <li>El cuerpo de la respuesta es una lista de {@link ComentarioDTO}.</li>
     * </ul>
     * @see co.edu.uniquindio.ProyectoGrupal.dtos.ComentarioDTO
     */
    @GetMapping("/alojamiento/{idAlojamiento}")
    public ResponseEntity<List<ComentarioDTO>> obtenerComentarios(@PathVariable String idAlojamiento) {
        List<ComentarioDTO> comentarios = comentarioService.obtenerComentariosPorAlojamiento(idAlojamiento);
        return ResponseEntity.ok(comentarios);
    }
}
