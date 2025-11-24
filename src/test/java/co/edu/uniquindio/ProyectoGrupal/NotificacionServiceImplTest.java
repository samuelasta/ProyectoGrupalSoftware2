package co.edu.uniquindio.ProyectoGrupal;

import co.edu.uniquindio.ProyectoGrupal.Excepciones.UsuarioNoEncontradoException;
import co.edu.uniquindio.ProyectoGrupal.dtos.NotificacionDTO;
import co.edu.uniquindio.ProyectoGrupal.mapper.NotificacionMapper;
import co.edu.uniquindio.ProyectoGrupal.model.Notificacion;
import co.edu.uniquindio.ProyectoGrupal.model.Usuario;
import co.edu.uniquindio.ProyectoGrupal.repository.NotificacionRepository;
import co.edu.uniquindio.ProyectoGrupal.repository.UsuarioRepository;
import co.edu.uniquindio.ProyectoGrupal.service.impl.NotificacionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificacionServiceImplTest {

    @Mock
    private NotificacionRepository notificacionRepo;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private NotificacionMapper notificacionMapper;

    @InjectMocks
    private NotificacionServiceImpl notificacionService;

    private Usuario usuario;
    private Notificacion notificacion;
    private NotificacionDTO notificacionDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        usuario = new Usuario();
        usuario.setId("user-123");
        usuario.setNombre("Luis");

        notificacion = new Notificacion();
        notificacion.setId(UUID.randomUUID().toString());
        notificacion.setCheckIn(LocalDateTime.now());
        notificacion.setCheckOut(LocalDateTime.now().plusDays(2));
        notificacion.setNumeroHuespedes(2);
        notificacion.setEstado(co.edu.uniquindio.ProyectoGrupal.model.enums.EstadoReserva.ACTIVA);
        notificacion.setFechaCreacion(LocalDateTime.now());
        notificacion.setUsuario(usuario);

        notificacionDTO = new NotificacionDTO(
                null,
                notificacion.getCheckIn(),
                notificacion.getCheckOut(),
                notificacion.getNumeroHuespedes(),
                notificacion.getEstado(),
                notificacion.getFechaCreacion(),
                usuario.getId(),
                usuario.getNombre()
        );
    }

    @Test
    void registrarReserva_exito() {
        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));
        when(notificacionRepo.save(any(Notificacion.class))).thenReturn(notificacion);
        when(notificacionMapper.toDTO(notificacion)).thenReturn(notificacionDTO);

        NotificacionDTO result = notificacionService.registrarReserva(notificacionDTO);

        assertNotNull(result);
        assertEquals(usuario.getNombre(), result.usuarioNombre());
        verify(usuarioRepository).findById(usuario.getId());
        verify(notificacionRepo).save(any(Notificacion.class));
    }

    @Test
    void registrarReserva_usuarioNoExiste() {
        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.empty());

        assertThrows(UsuarioNoEncontradoException.class, () ->
                notificacionService.registrarReserva(notificacionDTO)
        );

        verify(usuarioRepository).findById(usuario.getId());
        verifyNoInteractions(notificacionRepo, notificacionMapper);
    }
}
