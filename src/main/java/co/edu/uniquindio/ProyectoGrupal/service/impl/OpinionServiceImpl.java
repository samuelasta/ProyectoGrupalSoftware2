package co.edu.uniquindio.ProyectoGrupal.service.impl;


import co.edu.uniquindio.ProyectoGrupal.Excepciones.RecursoNoEncontradoException;
import co.edu.uniquindio.ProyectoGrupal.dtos.OpinionRequestDTO;
import co.edu.uniquindio.ProyectoGrupal.dtos.OpinionResponseDTO;
import co.edu.uniquindio.ProyectoGrupal.mapper.OpinionMapper;
import co.edu.uniquindio.ProyectoGrupal.model.Alojamiento;
import co.edu.uniquindio.ProyectoGrupal.model.Comentario;
import co.edu.uniquindio.ProyectoGrupal.model.Reserva;
import co.edu.uniquindio.ProyectoGrupal.model.Usuario;
import co.edu.uniquindio.ProyectoGrupal.repository.AlojamientoRepository;
import co.edu.uniquindio.ProyectoGrupal.repository.ComentarioRepository;
import co.edu.uniquindio.ProyectoGrupal.repository.ReservaRepository;
import co.edu.uniquindio.ProyectoGrupal.repository.UsuarioRepository;
import co.edu.uniquindio.ProyectoGrupal.service.OpinionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpinionServiceImpl implements OpinionService {

    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final AlojamientoRepository alojamientoRepository;
    private final ReservaRepository reservaRepository;
    private final OpinionMapper opinionMapper;

    @Override
    public OpinionResponseDTO registrarOpinion(OpinionRequestDTO request) {

        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado"));

        Alojamiento alojamiento = alojamientoRepository.findById(request.alojamientoId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Alojamiento no encontrado"));

        Reserva reserva = reservaRepository.findById(request.reservaId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Reserva no encontrada"));

        Comentario comentario = opinionMapper.toEntity(request, usuario, alojamiento, reserva);
        comentarioRepository.save(comentario);

        return new OpinionResponseDTO(
                comentario.getId(),
                usuario.getNombre(),
                alojamiento.getTitulo(),
                comentario.getCalificacion(),
                comentario.getComentario()
        );
    }
}