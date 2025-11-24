package co.edu.uniquindio.ProyectoGrupal.service.impl;

import co.edu.uniquindio.ProyectoGrupal.Excepciones.UsuarioNoEncontradoException;
import co.edu.uniquindio.ProyectoGrupal.dtos.NotificacionDTO;
import co.edu.uniquindio.ProyectoGrupal.mapper.NotificacionMapper;
import co.edu.uniquindio.ProyectoGrupal.model.Notificacion;
import co.edu.uniquindio.ProyectoGrupal.model.Usuario;
import co.edu.uniquindio.ProyectoGrupal.repository.NotificacionRepository;
import co.edu.uniquindio.ProyectoGrupal.repository.UsuarioRepository;
import co.edu.uniquindio.ProyectoGrupal.service.NotificacionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Implementación del servicio de notificaciones para reservas.
 * Permite registrar una reserva y generar la notificación correspondiente.
 */
@Service
public class NotificacionServiceImpl implements NotificacionService {

    private final NotificacionRepository notificacionRepo;
    private final NotificacionMapper notificacionMapper;
    private final UsuarioRepository usuarioRepository;

    public NotificacionServiceImpl(NotificacionRepository notificacionRepo,
                                   NotificacionMapper notificacionMapper,
                                   UsuarioRepository usuarioRepository) {
        this.notificacionRepo = notificacionRepo;
        this.notificacionMapper = notificacionMapper;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Registra una nueva reserva generando la notificación correspondiente.
     *
     * @param notificacionDTO DTO con los datos de la reserva a registrar.
     * @return NotificacionDTO con los datos de la reserva registrada.
     * @throws UsuarioNoEncontradoException si el usuario indicado no existe en la base de datos.
     */
    @Override
    @Transactional
    public NotificacionDTO registrarReserva(NotificacionDTO notificacionDTO) {

        // Buscar la entidad Usuario correspondiente
        Usuario usuario = usuarioRepository.findById(notificacionDTO.usuarioId())
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));

        // Crear la entidad Notificacion
        Notificacion notificacion = new Notificacion();
        notificacion.setId(UUID.randomUUID().toString());
        notificacion.setCheckIn(notificacionDTO.checkIn());
        notificacion.setCheckOut(notificacionDTO.checkOut());
        notificacion.setNumeroHuespedes(notificacionDTO.numeroHuespedes());
        notificacion.setEstado(notificacionDTO.estado());
        notificacion.setFechaCreacion(notificacionDTO.fechaCreacion());
        notificacion.setUsuario(usuario); // Asignar el usuario

        // Guardar en la base de datos
        Notificacion guardada = notificacionRepo.save(notificacion);

        // Mapear a DTO y retornar
        return notificacionMapper.toDTO(guardada);
    }
}
