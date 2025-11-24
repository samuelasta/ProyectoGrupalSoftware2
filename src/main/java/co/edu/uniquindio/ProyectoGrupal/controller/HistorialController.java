package co.edu.uniquindio.ProyectoGrupal.controller;

import co.edu.uniquindio.ProyectoGrupal.Excepciones.UsuarioNoEncontradoException;
import co.edu.uniquindio.ProyectoGrupal.dtos.ReservaHistorialDTO;
import co.edu.uniquindio.ProyectoGrupal.service.HistorialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historial")
public class HistorialController {

    private final HistorialService historialService;

    public HistorialController(HistorialService historialService) {
        this.historialService = historialService;
    }

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

