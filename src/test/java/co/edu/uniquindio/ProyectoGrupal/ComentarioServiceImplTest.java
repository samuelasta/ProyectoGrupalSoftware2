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
/**
 * Clase de pruebas unitarias para {@link co.edu.uniquindio.ProyectoGrupal.service.impl.ComentarioServiceImpl}.
 * * Utiliza Mockito para simular (mockear) las dependencias de repositorio y mapper,
 * asegurando que solo se pruebe la lógica interna del servicio de comentarios.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 */
class ComentarioServiceImplTest {
    /**
     * Simulación del repositorio de comentarios. Se utiliza para controlar las respuestas de la base de datos.
     */
    @Mock
    private ComentarioRepository comentarioRepository;
    /**
     * Simulación del mapper de comentarios. Se utiliza para controlar la conversión de Entidad a DTO.
     */
    @Mock
    private ComentarioMapper comentarioMapper;
    /**
     * Instancia del servicio real, donde se inyectan las simulaciones (mocks).
     */
    @InjectMocks
    private ComentarioServiceImpl comentarioService;
    // Variables de configuración de datos de prueba
    private Usuario usuario;
    private Alojamiento alojamiento;
    private Comentario comentario1;
    private Comentario comentario2;
    private ComentarioDTO comentarioDTO1;
    private ComentarioDTO comentarioDTO2;
    /**
     * Método de configuración que se ejecuta antes de cada prueba.
     * * Inicializa los mocks de Mockito y crea los objetos de datos de prueba
     * (Usuario, Alojamiento, Comentario y ComentarioDTO) para ser utilizados en los tests.
     */
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
    /**
     * Prueba el escenario exitoso de {@code obtenerComentariosPorAlojamiento}.
     * * Verifica que el servicio:
     * 1. Llama al repositorio para obtener la lista de comentarios.
     * 2. Llama al mapper para convertir cada entidad a DTO.
     * 3. Retorna una lista de DTOs con el tamaño y contenido esperado.
     */
    @Test
    void obtenerComentariosPorAlojamiento_exito() {
        // Configuración (Mocking)
        when(comentarioRepository.findByAlojamiento_Id("aloj-1"))
                .thenReturn(Arrays.asList(comentario1, comentario2));

        when(comentarioMapper.toDTO(comentario1)).thenReturn(comentarioDTO1);
        when(comentarioMapper.toDTO(comentario2)).thenReturn(comentarioDTO2);
        // Ejecución
        List<ComentarioDTO> resultado = comentarioService.obtenerComentariosPorAlojamiento("aloj-1");
        // Verificación (Assertions)
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("com-1", resultado.get(0).id());
        assertEquals("com-2", resultado.get(1).id());
        // Verificación de interacciones (Mocking verification)
        verify(comentarioRepository).findByAlojamiento_Id("aloj-1");
        verify(comentarioMapper).toDTO(comentario1);
        verify(comentarioMapper).toDTO(comentario2);
    }
    /**
     * Prueba el escenario donde no existen comentarios para el ID de alojamiento dado.
     * * Verifica que el servicio:
     * 1. Llama al repositorio.
     * 2. Retorna una lista vacía.
     * 3. No invoca al mapper, ya que no hay entidades para convertir.
     */
    @Test
    void obtenerComentariosPorAlojamiento_listaVacia() {
        // Configuración (Mocking)
        when(comentarioRepository.findByAlojamiento_Id("aloj-no-existe"))
                .thenReturn(List.of());
        // Ejecución
        List<ComentarioDTO> resultado = comentarioService.obtenerComentariosPorAlojamiento("aloj-no-existe");
        // Verificación (Assertions)
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        // Verificación de interacciones (Mocking verification)
        verify(comentarioRepository).findByAlojamiento_Id("aloj-no-existe");
        verifyNoInteractions(comentarioMapper);
    }
}

