package co.edu.uniquindio.ProyectoGrupal;


import co.edu.uniquindio.ProyectoGrupal.Excepciones.ReservaNoCancelableException;
import co.edu.uniquindio.ProyectoGrupal.Excepciones.ReservaNoEncontradaException;
import co.edu.uniquindio.ProyectoGrupal.dtos.ReservaDTO;
import co.edu.uniquindio.ProyectoGrupal.mapper.ReservaMapper;
import co.edu.uniquindio.ProyectoGrupal.model.Reserva;
import co.edu.uniquindio.ProyectoGrupal.model.enums.EstadoReserva;
import co.edu.uniquindio.ProyectoGrupal.repository.ReservaRepository;
import co.edu.uniquindio.ProyectoGrupal.service.impl.ReservaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * Clase de pruebas unitarias para {@link co.edu.uniquindio.ProyectoGrupal.service.impl.ReservaServiceImpl}.
 * * Utiliza Mockito para simular (mockear) las dependencias de repositorio y mapper,
 * enfocándose en probar la lógica de negocio del método {@code cancelarReserva}
 * y el correcto manejo de las excepciones {@code ReservaNoEncontradaException} y
 * {@code ReservaNoCancelableException}.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 */
class ReservaServiceImplTest {
    /** Simulación del repositorio de Reservas. */
    @Mock
    private ReservaRepository reservaRepo;
    /** Simulación del Mapper para convertir la entidad Reserva a ReservaDTO. */
    @Mock
    private ReservaMapper reservaMapper;
    /** Instancia real del servicio a probar, con los mocks inyectados. */
    @InjectMocks
    private ReservaServiceImpl reservaService;
    // Entidades de prueba configuradas con diferentes estados
    private Reserva reservaActiva;
    private Reserva reservaFinalizada;
    private Reserva reservaCancelada;
    /**
     * Método de configuración que se ejecuta antes de cada prueba.
     * * Inicializa los mocks de Mockito y crea las entidades de reserva de prueba
     * con diferentes estados para cubrir todos los escenarios de cancelación.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Reserva activa
        reservaActiva = new Reserva();
        reservaActiva.setId("res-1");
        reservaActiva.setEstado(EstadoReserva.ACTIVA);

        // Reserva finalizada
        reservaFinalizada = new Reserva();
        reservaFinalizada.setId("res-2");
        reservaFinalizada.setEstado(EstadoReserva.FINALIZADA);

        // Reserva cancelada
        reservaCancelada = new Reserva();
        reservaCancelada.setId("res-3");
        reservaCancelada.setEstado(EstadoReserva.CANCELADA);
    }
    /**
     * Prueba el escenario exitoso de {@code cancelarReserva}.
     * * Verifica que una reserva en estado ACTIVA pueda ser cancelada,
     * su estado se actualice y se retorne el DTO con el nuevo estado CANCELADA.
     */
    @Test
    void cancelarReserva_exito() {
        // Configuración (Mocking)
        when(reservaRepo.findById("res-1")).thenReturn(Optional.of(reservaActiva));
        when(reservaRepo.save(reservaActiva)).thenReturn(reservaActiva);
        when(reservaMapper.toDTO(reservaActiva)).thenReturn(new ReservaDTO("res-1", EstadoReserva.CANCELADA));
        // Ejecución
        ReservaDTO resultado = reservaService.cancelarReserva("res-1");
        // Verificación (Assertions)
        assertNotNull(resultado);
        assertEquals(EstadoReserva.CANCELADA, resultado.estado());
        // Verificación de interacciones (Mocking verification)
        verify(reservaRepo).findById("res-1");
        verify(reservaRepo).save(reservaActiva);
        verify(reservaMapper).toDTO(reservaActiva);
    }
    /**
     * Prueba el escenario donde no existe una reserva con el ID proporcionado.
     * * Verifica que se lance {@link co.edu.uniquindio.ProyectoGrupal.Excepciones.ReservaNoEncontradaException}.
     */
    @Test
    void cancelarReserva_reservaNoEncontrada() {
        // Configuración (Mocking)
        when(reservaRepo.findById("res-999")).thenReturn(Optional.empty());
        // Ejecución y Verificación de Excepción
        assertThrows(ReservaNoEncontradaException.class, () ->
                reservaService.cancelarReserva("res-999")
        );
        // Verificación de interacciones
        verify(reservaRepo).findById("res-999");
        verifyNoInteractions(reservaMapper);
    }
    /**
     * Prueba el escenario donde se intenta cancelar una reserva en estado FINALIZADA.
     * * Verifica que se lance {@link co.edu.uniquindio.ProyectoGrupal.Excepciones.ReservaNoCancelableException}.
     */
    @Test
    void cancelarReserva_reservaFinalizada() {
        // Configuración (Mocking)
        when(reservaRepo.findById("res-2")).thenReturn(Optional.of(reservaFinalizada));
        // Ejecución y Verificación de Excepción
        assertThrows(ReservaNoCancelableException.class, () ->
                reservaService.cancelarReserva("res-2")
        );
        // Verificación de interacciones
        verify(reservaRepo).findById("res-2");
        verifyNoInteractions(reservaMapper);
    }
    /**
     * Prueba el escenario donde se intenta cancelar una reserva que ya está en estado CANCELADA.
     * * Verifica que se lance {@link co.edu.uniquindio.ProyectoGrupal.Excepciones.ReservaNoCancelableException}.
     */
    @Test
    void cancelarReserva_reservaYaCancelada() {
        // Configuración (Mocking)
        when(reservaRepo.findById("res-3")).thenReturn(Optional.of(reservaCancelada));
        // Ejecución y Verificación de Excepción
        assertThrows(ReservaNoCancelableException.class, () ->
                reservaService.cancelarReserva("res-3")
        );
        // Verificación de interacciones
        verify(reservaRepo).findById("res-3");
        verifyNoInteractions(reservaMapper);
    }
}
