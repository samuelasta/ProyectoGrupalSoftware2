package co.edu.uniquindio.ProyectoGrupal.controller;

import co.edu.uniquindio.ProyectoGrupal.dtos.NotificacionDTO;
import co.edu.uniquindio.ProyectoGrupal.service.NotificacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * Controlador REST para gestionar la funcionalidad de notificaciones y el registro de reservas
 * asociado a ellas (CU10).
 * * Este controlador expone un único endpoint para el registro de una reserva, lo cual
 * desencadena la creación de una notificación de confirmación.
 * * La ruta base para este controlador es {@code /notificaciones}.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 * @see co.edu.uniquindio.ProyectoGrupal.service.NotificacionService
 * @see co.edu.uniquindio.ProyectoGrupal.dtos.NotificacionDTO
 */
@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    private final NotificacionService notificacionService;
    /**
     * Constructor para la inyección de dependencias.
     * * Inyecta la implementación de {@link NotificacionService} necesaria para
     * ejecutar la lógica de negocio de las notificaciones.
     *
     * @param notificacionService El servicio de notificaciones inyectado por Spring.
     */
    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }
    /**
     * Maneja la petición POST para registrar una nueva reserva y generar su respectiva notificación.
     * * La ruta es {@code POST /notificaciones/registrar}.
     * * Este endpoint recibe los datos de la reserva y devuelve los detalles de la notificación generada.
     *
     * @param notificacionDTO El DTO que contiene la información de la reserva a registrar.
     * Se recibe en el cuerpo de la petición HTTP (Request Body).
     * @return {@code ResponseEntity<NotificacionDTO>} La respuesta HTTP que contiene:
     * <ul>
     * <li>Código de estado 200 (OK).</li>
     * <li>El cuerpo de la respuesta es un {@link NotificacionDTO} que representa la notificación
     * de confirmación generada tras el registro exitoso.</li>
     * </ul>
     */
    @PostMapping("/registrar")
    public ResponseEntity<NotificacionDTO> registrarReserva(@RequestBody NotificacionDTO notificacionDTO) {
        // Se delega la lógica de negocio al servicio. El nombre del método indica
        // que el registro de la reserva y la notificación son parte de la misma operación.
        NotificacionDTO dto = notificacionService.registrarReserva(notificacionDTO);
        // Se retorna la notificación generada con el código 200 OK.
        return ResponseEntity.ok(dto);
    }
}
