package co.edu.uniquindio.ProyectoGrupal;

import co.edu.uniquindio.ProyectoGrupal.Excepciones.UsuarioNoEncontradoException;
import co.edu.uniquindio.ProyectoGrupal.dtos.ReservaHistorialDTO;
import co.edu.uniquindio.ProyectoGrupal.mapper.ReservaHistorialMapper;
import co.edu.uniquindio.ProyectoGrupal.model.Reserva;
import co.edu.uniquindio.ProyectoGrupal.model.Usuario;
import co.edu.uniquindio.ProyectoGrupal.model.enums.EstadoReserva;
import co.edu.uniquindio.ProyectoGrupal.repository.ReservaRepository;
import co.edu.uniquindio.ProyectoGrupal.repository.UsuarioRepository;
import co.edu.uniquindio.ProyectoGrupal.service.impl.HistorialServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * Clase de pruebas unitarias para {@link co.edu.uniquindio.ProyectoGrupal.service.impl.HistorialServiceImpl}.
 * * Se utiliza Mockito para simular las dependencias clave (Repositorios y Mapper),
 * asegurando que se pruebe exclusivamente la lógica de negocio del servicio de historial.
 * * Las pruebas cubren tanto el flujo de obtención exitosa de datos como el manejo de excepciones.
 *
 * @author [Tu Nombre o el Nombre de tu Equipo]
 * @version 1.0
 */
class HistorialServiceImplTest {
    /**
     * Simulación del repositorio de Reservas.
     */
    @Mock
    private ReservaRepository reservaRepository;
    /**
     * Simulación del Mapper para convertir Reserva a ReservaHistorialDTO.
     */
    @Mock
    private ReservaHistorialMapper reservaMapper;
    /**
     * Simulación del Repositorio de Usuarios, necesario para validar la existencia del usuario.
     */
    @Mock
    private UsuarioRepository usuarioRepository;
    /**
     * Instancia real del servicio a probar, donde se inyectan las simulaciones.
     */
    @InjectMocks
    private HistorialServiceImpl historialService;
    // Variables de configuración de datos de prueba
    private Usuario usuario;
    private Reserva reserva;
    private ReservaHistorialDTO reservaDTO;
    /**
     * Método de configuración que se ejecuta antes de cada prueba.
     * * Inicializa los mocks de Mockito y crea los objetos de datos de prueba (Usuario, Reserva, ReservaHistorialDTO).
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Crear usuario de prueba
        usuario = new Usuario();
        usuario.setId("user-123");
        usuario.setNombre("Luis");

        // Crear reserva de prueba
        reserva = new Reserva();
        reserva.setId("res-456");
        reserva.setCheckIn(LocalDateTime.now());
        reserva.setCheckOut(LocalDateTime.now().plusDays(2));
        reserva.setNumeroHuespedes(2);
        reserva.setUsuario(usuario);
        reserva.setEstado(EstadoReserva.ACTIVA);        // Inicializar estado
        reserva.setFechaCreacion(LocalDateTime.now()); // Inicializar fechaCreacion

        // Crear DTO correspondiente
        reservaDTO = new ReservaHistorialDTO(
                reserva.getId(),
                reserva.getCheckIn(),
                reserva.getCheckOut(),
                reserva.getNumeroHuespedes(),
                reserva.getEstado(),
                reserva.getFechaCreacion()
        );
    }
    /**
     * Prueba el escenario exitoso de {@code obtenerReservasUsuario}.
     * * Verifica que el servicio:
     * 1. Confirma la existencia del usuario.
     * 2. Llama al repositorio para obtener las reservas del usuario.
     * 3. Mapea la entidad a DTO y retorna la lista esperada.
     */
    @Test
    void obtenerReservasUsuario_exito() {
        // Mock comportamiento
        when(usuarioRepository.existsById("user-123")).thenReturn(true);

        List<Reserva> reservas = new ArrayList<>();
        reservas.add(reserva);

        when(reservaRepository.findByUsuario_Id("user-123")).thenReturn(reservas);
        when(reservaMapper.toDTO(reserva)).thenReturn(reservaDTO);

        // Llamada al servicio
        List<ReservaHistorialDTO> resultado = historialService.obtenerReservasUsuario("user-123");
        // Verificación (Assertions)
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("res-456", resultado.get(0).id());
        // Verificación de interacciones (Mocking verification)
        verify(usuarioRepository).existsById("user-123");
        verify(reservaRepository).findByUsuario_Id("user-123");
        verify(reservaMapper).toDTO(reserva);
    }
    /**
     * Prueba el escenario en el que el ID de usuario proporcionado no existe en el sistema.
     * * Verifica que el servicio:
     * 1. Llama a la validación de existencia.
     * 2. Lanza {@link co.edu.uniquindio.ProyectoGrupal.Excepciones.UsuarioNoEncontradoException}.
     * 3. No realiza llamadas a los repositorios ni mappers de reserva.
     */
    @Test
    void obtenerReservasUsuario_usuarioNoExiste() {
        // Configuración (Mocking)
        when(usuarioRepository.existsById("user-999")).thenReturn(false);
        // Ejecución y Verificación de Excepción (Assertions)
        assertThrows(UsuarioNoEncontradoException.class, () ->
                historialService.obtenerReservasUsuario("user-999")
        );
        // Verificación de interacciones (Mocking verification)
        verify(usuarioRepository).existsById("user-999");
        verifyNoInteractions(reservaRepository, reservaMapper);
    }
}

