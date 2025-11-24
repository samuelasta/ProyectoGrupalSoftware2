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

class ReservaServiceImplTest {

    @Mock
    private ReservaRepository reservaRepo;

    @Mock
    private ReservaMapper reservaMapper;

    @InjectMocks
    private ReservaServiceImpl reservaService;

    private Reserva reservaActiva;
    private Reserva reservaFinalizada;
    private Reserva reservaCancelada;

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

    @Test
    void cancelarReserva_exito() {
        when(reservaRepo.findById("res-1")).thenReturn(Optional.of(reservaActiva));
        when(reservaRepo.save(reservaActiva)).thenReturn(reservaActiva);
        when(reservaMapper.toDTO(reservaActiva)).thenReturn(new ReservaDTO("res-1", EstadoReserva.CANCELADA));

        ReservaDTO resultado = reservaService.cancelarReserva("res-1");

        assertNotNull(resultado);
        assertEquals(EstadoReserva.CANCELADA, resultado.estado());
        verify(reservaRepo).findById("res-1");
        verify(reservaRepo).save(reservaActiva);
        verify(reservaMapper).toDTO(reservaActiva);
    }

    @Test
    void cancelarReserva_reservaNoEncontrada() {
        when(reservaRepo.findById("res-999")).thenReturn(Optional.empty());

        assertThrows(ReservaNoEncontradaException.class, () ->
                reservaService.cancelarReserva("res-999")
        );

        verify(reservaRepo).findById("res-999");
        verifyNoInteractions(reservaMapper);
    }

    @Test
    void cancelarReserva_reservaFinalizada() {
        when(reservaRepo.findById("res-2")).thenReturn(Optional.of(reservaFinalizada));

        assertThrows(ReservaNoCancelableException.class, () ->
                reservaService.cancelarReserva("res-2")
        );

        verify(reservaRepo).findById("res-2");
        verifyNoInteractions(reservaMapper);
    }

    @Test
    void cancelarReserva_reservaYaCancelada() {
        when(reservaRepo.findById("res-3")).thenReturn(Optional.of(reservaCancelada));

        assertThrows(ReservaNoCancelableException.class, () ->
                reservaService.cancelarReserva("res-3")
        );

        verify(reservaRepo).findById("res-3");
        verifyNoInteractions(reservaMapper);
    }
}
