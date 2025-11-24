package co.edu.uniquindio.ProyectoGrupal.controller;

import co.edu.uniquindio.ProyectoGrupal.Excepciones.UsuarioNoEncontradoException;
import co.edu.uniquindio.ProyectoGrupal.dtos.ReservaHistorialDTO;
import co.edu.uniquindio.ProyectoGrupal.service.HistorialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controlador REST para gestionar la consulta del historial de reservas de un usuario.
 * * Este controlador maneja las peticiones HTTP relacionadas con la obtención de las reservas
 * pasadas y futuras de un usuario específico.
 * * La ruta base para todos los endpoints de este controlador es {@code /historial}.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 * @see co.edu.uniquindio.ProyectoGrupal.service.HistorialService
 * @see co.edu.uniquindio.ProyectoGrupal.dtos.ReservaHistorialDTO
 */
@RestController
@RequestMapping("/historial")
public class HistorialController {

    private final HistorialService historialService;
    /**
     * Constructor para la inyección de dependencias.
     * * Inyecta la implementación de {@link HistorialService} necesaria para
     * acceder a la lógica de negocio del historial de reservas.
     *
     * @param historialService El servicio de historial de reservas inyectado por Spring.
     */
    public HistorialController(HistorialService historialService) {
        this.historialService = historialService;
    }
    /**
     * Maneja la petición GET para obtener el historial completo de reservas de un usuario.
     * * La ruta es {@code GET /historial/usuario/{idUsuario}}.
     * * Implementa manejo de excepciones para retornar códigos de estado HTTP apropiados.
     *
     * @param idUsuario El ID único del usuario cuyo historial de reservas se desea consultar.
     * @return {@code ResponseEntity<List<ReservaHistorialDTO>>} La respuesta HTTP que contiene:
     * <ul>
     * <li>Código de estado 200 (OK) y la lista de reservas si el historial es encontrado y contiene datos.</li>
     * <li>Código de estado 204 (No Content) si el usuario existe, pero no tiene reservas registradas.</li>
     * <li>Código de estado 404 (Not Found) si se lanza una {@link UsuarioNoEncontradoException}.</li>
     * </ul>
     */
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ReservaHistorialDTO>> obtenerHistorial(@PathVariable String idUsuario) {
        try {
            List<ReservaHistorialDTO> historial = historialService.obtenerReservasUsuario(idUsuario);
            if (historial.isEmpty()) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(historial);
        } catch (UsuarioNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

