package co.edu.uniquindio.ProyectoGrupal.controller;

import co.edu.uniquindio.ProyectoGrupal.dtos.NotificacionDTO;
import co.edu.uniquindio.ProyectoGrupal.service.NotificacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    private final NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<NotificacionDTO> registrarReserva(@RequestBody NotificacionDTO notificacionDTO) {
        NotificacionDTO dto = notificacionService.registrarReserva(notificacionDTO);
        return ResponseEntity.ok(dto);
    }
}
