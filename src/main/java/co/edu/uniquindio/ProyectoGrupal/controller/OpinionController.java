package co.edu.uniquindio.ProyectoGrupal.controller;

import co.edu.uniquindio.ProyectoGrupal.Excepciones.RecursoNoEncontradoException;
import co.edu.uniquindio.ProyectoGrupal.dtos.OpinionRequestDTO;
import co.edu.uniquindio.ProyectoGrupal.dtos.OpinionResponseDTO;
import co.edu.uniquindio.ProyectoGrupal.service.OpinionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Controlador REST encargado de gestionar las operaciones relacionadas con las opiniones
 * y valoraciones de los usuarios sobre los alojamientos.
 * * Utiliza la anotación {@code @RequiredArgsConstructor} de Lombok para inyectar la dependencia
 * {@link OpinionService} a través del constructor.
 * * La ruta base para este controlador es {@code /opiniones}.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 * @see co.edu.uniquindio.ProyectoGrupal.service.OpinionService
 * @see co.edu.uniquindio.ProyectoGrupal.dtos.OpinionRequestDTO
 * @see co.edu.uniquindio.ProyectoGrupal.dtos.OpinionResponseDTO
 */
@RestController
@RequestMapping("/opiniones")
@RequiredArgsConstructor
public class OpinionController {

    private final OpinionService opinionService;

    @PostMapping
    public ResponseEntity<OpinionResponseDTO> registrarOpinion(@RequestBody OpinionRequestDTO request) {
        try {
            // El servicio lanzará RecursoNoEncontradoException si el usuario o alojamiento no existe
            OpinionResponseDTO response = opinionService.registrarOpinion(request);
            // Retorno 200 OK si es exitoso
            return ResponseEntity.ok(response);

        } catch (RecursoNoEncontradoException e) {
            // Retorno 404 Not Found si el usuario o alojamiento no existe
            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            // Retorno 500 Internal Server Error para cualquier otra excepción inesperada
            return ResponseEntity.internalServerError().build();
        }
    }
}
