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

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

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


