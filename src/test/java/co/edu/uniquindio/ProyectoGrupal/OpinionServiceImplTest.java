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

class OpinionServiceImplTest {

    @Mock
    ComentarioRepository comentarioRepository;

    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    AlojamientoRepository alojamientoRepository;

    @Mock
    ReservaRepository reservaRepository;

    @Mock
    OpinionMapper opinionMapper;

    @InjectMocks
    OpinionServiceImpl opinionService;

    private OpinionRequestDTO request;
    private Usuario usuario;
    private Alojamiento alojamiento;
    private Reserva reserva;
    private Comentario comentario;

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

    @Test
    void registrarOpinion_exito() {

        when(usuarioRepository.findById("1")).thenReturn(Optional.of(usuario));
        when(alojamientoRepository.findById("2")).thenReturn(Optional.of(alojamiento));
        when(reservaRepository.findById("3")).thenReturn(Optional.of(reserva));

        when(opinionMapper.toEntity(request, usuario, alojamiento, reserva))
                .thenReturn(comentario);

        when(comentarioRepository.save(comentario)).thenReturn(comentario);

        OpinionResponseDTO response = opinionService.registrarOpinion(request);

        assertNotNull(response);
        assertEquals("uuid-12345", response.id());
        assertEquals("Daniel", response.usuarioNombre());
        assertEquals("Cabaña en Salento", response.alojamientoTitulo());
        assertEquals(5, response.calificacion());
        assertEquals("Muy buen sitio", response.comentario());

        verify(usuarioRepository).findById("1");
        verify(alojamientoRepository).findById("2");
        verify(reservaRepository).findById("3");
        verify(comentarioRepository).save(comentario);
    }

    @Test
    void registrarOpinion_usuarioNoExiste() {

        when(usuarioRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(RecursoNoEncontradoException.class, () ->
                opinionService.registrarOpinion(request)
        );

        verify(usuarioRepository).findById("1");
        verifyNoInteractions(alojamientoRepository, reservaRepository, comentarioRepository);
    }
}

