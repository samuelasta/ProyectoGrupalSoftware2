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
/**
 * Clase de pruebas unitarias para {@link co.edu.uniquindio.ProyectoGrupal.service.impl.NotificacionServiceImpl}.
 * * Utiliza Mockito para simular las dependencias (Repositorios y Mapper) y garantizar
 * que la lógica de negocio del servicio para el registro de reservas/notificaciones sea probada
 * de forma aislada.
 * * Las pruebas cubren el flujo de creación de notificaciones y la validación de la existencia del usuario.
 *
 * @author [Tu Nombre o el Nombre de tu Equipo]
 * @version 1.0
 */
class NotificacionServiceImplTest {
    /**
     * Simulación del repositorio de Notificaciones (para persistencia).
     */
    @Mock
    private NotificacionRepository notificacionRepo;
    /**
     * Simulación del repositorio de Usuarios (para validación de existencia).
     */
    @Mock
    private UsuarioRepository usuarioRepository;
    /**
     * Simulación del Mapper para la conversión entre Notificacion y NotificacionDTO.
     */
    @Mock
    private NotificacionMapper notificacionMapper;
    /**
     * Instancia real del servicio a probar, con los mocks inyectados.
     */
    @InjectMocks
    private NotificacionServiceImpl notificacionService;
    // Variables de configuración de datos de prueba
    private Usuario usuario;
    private Notificacion notificacion;
    private NotificacionDTO notificacionDTO;
    /**
     * Método de configuración que se ejecuta antes de cada prueba.
     * * Inicializa los mocks y establece los objetos de datos necesarios para la prueba
     * (Usuario, Notificacion, NotificacionDTO).
     */
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
        // El DTO de entrada para una nueva reserva/notificación no debe tener ID
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
    /**
     * Prueba el escenario exitoso de {@code registrarReserva}.
     * * Verifica que el servicio:
     * 1. Confirma la existencia del usuario.
     * 2. Llama al repositorio para guardar la nueva notificación.
     * 3. Llama al mapper para convertir la entidad persistida a DTO.
     * 4. Retorna el DTO de resultado con el nombre del usuario.
     */
    @Test
    void registrarReserva_exito() {
        // Configuración (Mocking): Simular que el usuario existe y que la persistencia es exitosa.
        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));
        when(notificacionRepo.save(any(Notificacion.class))).thenReturn(notificacion);
        when(notificacionMapper.toDTO(notificacion)).thenReturn(notificacionDTO);
        // Ejecución
        NotificacionDTO result = notificacionService.registrarReserva(notificacionDTO);
        // Verificación (Assertions)
        assertNotNull(result);
        assertEquals(usuario.getNombre(), result.usuarioNombre());
        // Verificación de interacciones (Mocking verification)
        verify(usuarioRepository).findById(usuario.getId());
        verify(notificacionRepo).save(any(Notificacion.class));
        verify(notificacionMapper).toDTO(notificacion);
    }
    /**
     * Prueba el escenario de fallo cuando el usuario referenciado no existe.
     * * Verifica que el servicio:
     * 1. Llama al repositorio de usuario.
     * 2. Lanza {@link co.edu.uniquindio.ProyectoGrupal.Excepciones.UsuarioNoEncontradoException}.
     * 3. No interactúa con el repositorio de Notificaciones ni con el mapper.
     */
    @Test
    void registrarReserva_usuarioNoExiste() {
        // Configuración (Mocking): Simular que el usuario no es encontrado.
        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.empty());
        // Ejecución y Verificación de Excepción (Assertions)
        assertThrows(UsuarioNoEncontradoException.class, () ->
                notificacionService.registrarReserva(notificacionDTO)
        );
        // Verificación de interacciones (Mocking verification)
        verify(usuarioRepository).findById(usuario.getId());
        verifyNoInteractions(notificacionRepo, notificacionMapper);
    }
}
