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

class HistorialServiceImplTest {

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private ReservaHistorialMapper reservaMapper;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private HistorialServiceImpl historialService;

    private Usuario usuario;
    private Reserva reserva;
    private ReservaHistorialDTO reservaDTO;

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

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("res-456", resultado.get(0).id());

        verify(usuarioRepository).existsById("user-123");
        verify(reservaRepository).findByUsuario_Id("user-123");
        verify(reservaMapper).toDTO(reserva);
    }

    @Test
    void obtenerReservasUsuario_usuarioNoExiste() {
        when(usuarioRepository.existsById("user-999")).thenReturn(false);

        assertThrows(UsuarioNoEncontradoException.class, () ->
                historialService.obtenerReservasUsuario("user-999")
        );

        verify(usuarioRepository).existsById("user-999");
        verifyNoInteractions(reservaRepository, reservaMapper);
    }
}

