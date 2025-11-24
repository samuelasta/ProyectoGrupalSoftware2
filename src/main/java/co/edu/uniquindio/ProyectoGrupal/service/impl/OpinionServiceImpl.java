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
/**
 * Implementación de la interfaz {@link co.edu.uniquindio.ProyectoGrupal.service.OpinionService}.
 * * Contiene la lógica de negocio para registrar y gestionar las opiniones/comentarios
 * de los usuarios sobre los alojamientos.
 * * Este servicio coordina múltiples repositorios para asegurar la integridad de los datos
 * antes de la persistencia.
 * * Utiliza {@code @RequiredArgsConstructor} de Lombok para la inyección de dependencias a través de constructor.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class OpinionServiceImpl implements OpinionService {

    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final AlojamientoRepository alojamientoRepository;
    private final ReservaRepository reservaRepository;
    private final OpinionMapper opinionMapper;
    /**
     * Registra una nueva opinión (Comentario) en el sistema.
     * * El proceso garantiza que todos los recursos referenciados en el DTO (Usuario, Alojamiento, Reserva)
     * existan antes de la persistencia.
     *
     * @param request El {@link co.edu.uniquindio.ProyectoGrupal.dtos.OpinionRequestDTO} con los datos de la opinión.
     * @return El {@link co.edu.uniquindio.ProyectoGrupal.dtos.OpinionResponseDTO} con los datos de la opinión registrada.
     * @throws RecursoNoEncontradoException Si el ID del usuario, alojamiento o reserva proporcionado no existe.
     */
    @Override
    public OpinionResponseDTO registrarOpinion(OpinionRequestDTO request) {
// 1. Validación de existencia de recursos.
        // Se utiliza .orElseThrow() con la excepción de negocio RecursoNoEncontradoException
        // para garantizar que solo se pueda opinar si el Usuario, Alojamiento y Reserva existen.
        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado"));

        Alojamiento alojamiento = alojamientoRepository.findById(request.alojamientoId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Alojamiento no encontrado"));

        Reserva reserva = reservaRepository.findById(request.reservaId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Reserva no encontrada"));
        // 2. Mapeo a entidad.
        // Se utiliza el mapper para ensamblar la entidad Comentario, inyectando las entidades
        // de recursos ya cargadas (Usuario, Alojamiento, Reserva).
        Comentario comentario = opinionMapper.toEntity(request, usuario, alojamiento, reserva);
        // 3. Persistencia.
        comentarioRepository.save(comentario);
        // 4. Mapeo a respuesta DTO.
        // Se construye el DTO de respuesta utilizando la entidad persistida y los datos cargados.
        return new OpinionResponseDTO(
                comentario.getId(),
                usuario.getNombre(),
                alojamiento.getTitulo(),
                comentario.getCalificacion(),
                comentario.getComentario()
        );
    }
}