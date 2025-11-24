package co.edu.uniquindio.ProyectoGrupal;

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
import co.edu.uniquindio.ProyectoGrupal.service.impl.OpinionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * Clase de pruebas unitarias para {@link co.edu.uniquindio.ProyectoGrupal.service.impl.OpinionServiceImpl}.
 * * Utiliza Mockito para simular (mockear) las múltiples dependencias de repositorio y el mapper,
 * enfocándose en probar la lógica de validación de recursos y el ensamblaje de la entidad Comentario.
 *
 * @author [Tu Nombre o el Nombre de tu Equipo]
 * @version 1.0
 */
class OpinionServiceImplTest {
    /** Simulación del repositorio de Comentarios (para persistencia). */
    @Mock
    ComentarioRepository comentarioRepository;
    /** Simulación del repositorio de Usuarios (para validación). */
    @Mock
    UsuarioRepository usuarioRepository;
    /** Simulación del repositorio de Alojamientos (para validación). */
    @Mock
    AlojamientoRepository alojamientoRepository;
    /** Simulación del repositorio de Reservas (para validación). */
    @Mock
    ReservaRepository reservaRepository;
    /** Simulación del Mapper para ensamblar la entidad Comentario. */
    @Mock
    OpinionMapper opinionMapper;
    /** Instancia real del servicio a probar, con los mocks inyectados. */
    @InjectMocks
    OpinionServiceImpl opinionService;
    // Variables de configuración de datos de prueba
    private OpinionRequestDTO request;
    private Usuario usuario;
    private Alojamiento alojamiento;
    private Reserva reserva;
    private Comentario comentario;
    /**
     * Método de configuración que se ejecuta antes de cada prueba.
     * * Inicializa los mocks y crea los objetos de datos necesarios para la prueba (DTO, Entidades).
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        request = new OpinionRequestDTO("1", "2", "3", 5, "Muy buen sitio");

        usuario = new Usuario();
        usuario.setId("1");
        usuario.setNombre("Daniel");

        alojamiento = new Alojamiento();
        alojamiento.setId("2");
        alojamiento.setTitulo("Cabaña en Salento");

        reserva = new Reserva();
        reserva.setId("3");

        comentario = new Comentario();
        comentario.setId("uuid-12345");
        comentario.setCalificacion(5);
        comentario.setComentario("Muy buen sitio");
    }
    /**
     * Prueba el escenario exitoso de {@code registrarOpinion}.
     * * Verifica el flujo completo:
     * 1. Carga exitosa de Usuario, Alojamiento y Reserva.
     * 2. Conversión a entidad usando el mapper.
     * 3. Persistencia de la entidad Comentario.
     * 4. Retorno del DTO de respuesta correcto con los detalles cargados.
     */
    @Test
    void registrarOpinion_exito() {
        // 1. Configuración (Mocking) de la carga de recursos:
        when(usuarioRepository.findById("1")).thenReturn(Optional.of(usuario));
        when(alojamientoRepository.findById("2")).thenReturn(Optional.of(alojamiento));
        when(reservaRepository.findById("3")).thenReturn(Optional.of(reserva));
        // 2. Configuración (Mocking) del mapeo:
        when(opinionMapper.toEntity(request, usuario, alojamiento, reserva))
                .thenReturn(comentario);
        // 3. Configuración (Mocking) de la persistencia:
        when(comentarioRepository.save(comentario)).thenReturn(comentario);
        // Ejecución
        OpinionResponseDTO response = opinionService.registrarOpinion(request);
        // Verificación (Assertions)
        assertNotNull(response);
        assertEquals("uuid-12345", response.id());
        assertEquals("Daniel", response.usuarioNombre());
        assertEquals("Cabaña en Salento", response.alojamientoTitulo());
        assertEquals(5, response.calificacion());
        assertEquals("Muy buen sitio", response.comentario());
        // Verificación de interacciones (Mocking verification)
        verify(usuarioRepository).findById("1");
        verify(alojamientoRepository).findById("2");
        verify(reservaRepository).findById("3");
        verify(comentarioRepository).save(comentario);
    }
    /**
     * Prueba el escenario de fallo cuando el Usuario referenciado no existe.
     * * Verifica que el servicio:
     * 1. Lanza {@link co.edu.uniquindio.ProyectoGrupal.Excepciones.RecursoNoEncontradoException}.
     * 2. Detiene el proceso inmediatamente después de la falla del primer repositorio.
     */
    @Test
    void registrarOpinion_usuarioNoExiste() {
        // Configuración (Mocking) de la falla del recurso:
        when(usuarioRepository.findById("1")).thenReturn(Optional.empty());
        // Ejecución y Verificación de Excepción (Assertions)
        assertThrows(RecursoNoEncontradoException.class, () ->
                opinionService.registrarOpinion(request)
        );
        // Verificación de interacciones (Mocking verification)
        verify(usuarioRepository).findById("1");
        verifyNoInteractions(alojamientoRepository, reservaRepository, comentarioRepository);
    }
}

