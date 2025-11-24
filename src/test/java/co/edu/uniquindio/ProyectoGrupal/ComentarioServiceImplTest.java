package co.edu.uniquindio.ProyectoGrupal;

import co.edu.uniquindio.ProyectoGrupal.dtos.ComentarioDTO;
import co.edu.uniquindio.ProyectoGrupal.mapper.ComentarioMapper;
import co.edu.uniquindio.ProyectoGrupal.model.Alojamiento;
import co.edu.uniquindio.ProyectoGrupal.model.Comentario;
import co.edu.uniquindio.ProyectoGrupal.model.Usuario;
import co.edu.uniquindio.ProyectoGrupal.repository.ComentarioRepository;
import co.edu.uniquindio.ProyectoGrupal.service.impl.ComentarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ComentarioServiceImplTest {

    @Mock
    private ComentarioRepository comentarioRepository;

    @Mock
    private ComentarioMapper comentarioMapper;

    @InjectMocks
    private ComentarioServiceImpl comentarioService;

    private Usuario usuario;
    private Alojamiento alojamiento;
    private Comentario comentario1;
    private Comentario comentario2;
    private ComentarioDTO comentarioDTO1;
    private ComentarioDTO comentarioDTO2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        usuario = new Usuario();
        usuario.setId("user-1");
        usuario.setNombre("Carlos");

        alojamiento = new Alojamiento();
        alojamiento.setId("aloj-1");

        comentario1 = new Comentario();
        comentario1.setId("com-1");
        comentario1.setComentario("Muy bueno");
        comentario1.setCalificacion(5);
        comentario1.setUsuario(usuario);
        comentario1.setAlojamiento(alojamiento);

        comentario2 = new Comentario();
        comentario2.setId("com-2");
        comentario2.setComentario("Regular");
        comentario2.setCalificacion(3);
        comentario2.setUsuario(usuario);
        comentario2.setAlojamiento(alojamiento);

        comentarioDTO1 = new ComentarioDTO("com-1", "Muy bueno", 5, "Carlos");
        comentarioDTO2 = new ComentarioDTO("com-2", "Regular", 3, "Carlos");
    }

    @Test
    void obtenerComentariosPorAlojamiento_exito() {
        when(comentarioRepository.findByAlojamiento_Id("aloj-1"))
                .thenReturn(Arrays.asList(comentario1, comentario2));

        when(comentarioMapper.toDTO(comentario1)).thenReturn(comentarioDTO1);
        when(comentarioMapper.toDTO(comentario2)).thenReturn(comentarioDTO2);

        List<ComentarioDTO> resultado = comentarioService.obtenerComentariosPorAlojamiento("aloj-1");

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("com-1", resultado.get(0).id());
        assertEquals("com-2", resultado.get(1).id());

        verify(comentarioRepository).findByAlojamiento_Id("aloj-1");
        verify(comentarioMapper).toDTO(comentario1);
        verify(comentarioMapper).toDTO(comentario2);
    }

    @Test
    void obtenerComentariosPorAlojamiento_listaVacia() {
        when(comentarioRepository.findByAlojamiento_Id("aloj-no-existe"))
                .thenReturn(List.of());

        List<ComentarioDTO> resultado = comentarioService.obtenerComentariosPorAlojamiento("aloj-no-existe");

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());

        verify(comentarioRepository).findByAlojamiento_Id("aloj-no-existe");
        verifyNoInteractions(comentarioMapper);
    }
}

