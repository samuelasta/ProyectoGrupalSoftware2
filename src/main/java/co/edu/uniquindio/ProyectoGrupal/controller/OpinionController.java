package co.edu.uniquindio.ProyectoGrupal.controller;

import co.edu.uniquindio.ProyectoGrupal.dtos.OpinionRequestDTO;
import co.edu.uniquindio.ProyectoGrupal.dtos.OpinionResponseDTO;
import co.edu.uniquindio.ProyectoGrupal.service.OpinionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/opiniones")
@RequiredArgsConstructor
public class OpinionController {

    private final OpinionService opinionService;

    @PostMapping
    public ResponseEntity<OpinionResponseDTO> registrarOpinion(@RequestBody OpinionRequestDTO request) {
        OpinionResponseDTO response = opinionService.registrarOpinion(request);
        return ResponseEntity.ok(response);
    }
}
