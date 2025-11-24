package co.edu.uniquindio.ProyectoGrupal.controller;

import co.edu.uniquindio.ProyectoGrupal.Excepciones.ReservaNoCancelableException;
import co.edu.uniquindio.ProyectoGrupal.Excepciones.ReservaNoEncontradaException;
import co.edu.uniquindio.ProyectoGrupal.dtos.ReservaDTO;
import co.edu.uniquindio.ProyectoGrupal.service.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
/**
 * Controlador REST para gestionar las operaciones relacionadas con las reservas,
 * específicamente la cancelación de las mismas.
 * * Este controlador expone un endpoint para modificar el estado de una reserva a 'CANCELADA'.
 * * La ruta base para este controlador es {@code /reservas}.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 * @see co.edu.uniquindio.ProyectoGrupal.service.ReservaService
 * @see co.edu.uniquindio.ProyectoGrupal.dtos.ReservaDTO
 */
@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;
    /**
     * Constructor para la inyección de dependencias.
     * * Inyecta la implementación de {@link ReservaService} necesaria para
     * acceder a la lógica de negocio de las reservas.
     *
     * @param reservaService El servicio de reservas inyectado por Spring.
     */
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }
    /**
     * Maneja la petición PUT para cancelar una reserva específica por su ID.
     * * La ruta es {@code PUT /reservas/cancelar/{id}}.
     * * Implementa manejo de excepciones para retornar códigos de estado HTTP apropiados
     * en caso de fallos en la lógica de negocio.
     *
     * @param id El ID único de la reserva que se desea cancelar. Se extrae como variable de ruta.
     * @return {@code ResponseEntity<?>} La respuesta HTTP que contiene:
     * <ul>
     * <li>Código de estado 200 (OK) y el {@link ReservaDTO} actualizado si la cancelación es exitosa.</li>
     * <li>Código de estado 400 (Bad Request) si ocurre alguna de las siguientes excepciones:
     * <ul>
     * <li>{@link ReservaNoEncontradaException}: La reserva con el ID proporcionado no existe.</li>
     * <li>{@link ReservaNoCancelableException}: La reserva existe, pero no cumple con las
     * reglas de negocio para ser cancelada (ej. ya pasó la fecha de check-in).</li>
     * </ul>
     * En caso de 400, el cuerpo de la respuesta contendrá un mapa con la clave "error" y el mensaje de la excepción.
     * </li>
     * </ul>
     */
    @PutMapping("/cancelar/{id}")
    public ResponseEntity<?> cancelarReserva(@PathVariable String id) {
        try {
            ReservaDTO reservaDTO = reservaService.cancelarReserva(id);
            return ResponseEntity.ok(reservaDTO);
        } catch (ReservaNoEncontradaException | ReservaNoCancelableException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}


