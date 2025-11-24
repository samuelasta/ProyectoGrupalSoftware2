package co.edu.uniquindio.ProyectoGrupal.service.impl;

import co.edu.uniquindio.ProyectoGrupal.dtos.ComentarioDTO;
import co.edu.uniquindio.ProyectoGrupal.mapper.ComentarioMapper;
import co.edu.uniquindio.ProyectoGrupal.model.Comentario;
import co.edu.uniquindio.ProyectoGrupal.repository.ComentarioRepository;
import co.edu.uniquindio.ProyectoGrupal.service.ComentarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    private final ComentarioRepository comentarioRepo;
    private final ComentarioMapper comentarioMapper;

    public ComentarioServiceImpl(ComentarioRepository comentarioRepo, ComentarioMapper comentarioMapper) {
        this.comentarioRepo = comentarioRepo;
        this.comentarioMapper = comentarioMapper;
    }

    @Override
    public List<ComentarioDTO> obtenerComentariosPorAlojamiento(String idAlojamiento) {
        List<Comentario> comentarios = comentarioRepo.findByAlojamiento_Id(idAlojamiento);
        return comentarios.stream()
                .map(comentarioMapper::toDTO)
                .collect(Collectors.toList());
    }
}