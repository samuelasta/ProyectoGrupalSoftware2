package co.edu.uniquindio.ProyectoGrupal.controller;


import co.edu.uniquindio.ProyectoGrupal.dtos.ComentarioDTO;
import co.edu.uniquindio.ProyectoGrupal.service.ComentarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @GetMapping("/alojamiento/{idAlojamiento}")
    public ResponseEntity<List<ComentarioDTO>> obtenerComentarios(@PathVariable String idAlojamiento) {
        List<ComentarioDTO> comentarios = comentarioService.obtenerComentariosPorAlojamiento(idAlojamiento);
        return ResponseEntity.ok(comentarios);
    }
}
