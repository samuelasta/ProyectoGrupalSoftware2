package co.edu.uniquindio.ProyectoGrupal.service.impl;


import co.edu.uniquindio.ProyectoGrupal.Excepciones.ReservaNoCancelableException;
import co.edu.uniquindio.ProyectoGrupal.Excepciones.ReservaNoEncontradaException;
import co.edu.uniquindio.ProyectoGrupal.dtos.ReservaDTO;

import co.edu.uniquindio.ProyectoGrupal.mapper.ReservaMapper;
import co.edu.uniquindio.ProyectoGrupal.model.Reserva;
import co.edu.uniquindio.ProyectoGrupal.model.enums.EstadoReserva;

import co.edu.uniquindio.ProyectoGrupal.repository.ReservaRepository;
import co.edu.uniquindio.ProyectoGrupal.service.ReservaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * Implementación del servicio de reservas que maneja las operaciones
 * relacionadas con la gestión de reservas, como la cancelación.
 * <p>
 * Esta clase se encarga de la lógica de negocio y utiliza un repositorio
 * para acceder a la base de datos y un mapper para convertir las entidades
 * a DTOs.
 * </p>
 */
@Service
public class ReservaServiceImpl implements ReservaService {
    /** Repositorio de reservas para operaciones CRUD */
    private final ReservaRepository reservaRepo;
    /** Mapper para convertir entre entidad Reserva y ReservaDTO */
    private final ReservaMapper reservaMapper;

    /**
     * Constructor de la clase con inyección de dependencias.
     *
     * @param reservaRepo   repositorio de reservas
     * @param reservaMapper mapper para conversión a DTO
     */
    public ReservaServiceImpl(ReservaRepository reservaRepo, ReservaMapper reservaMapper) {
        this.reservaRepo = reservaRepo;
        this.reservaMapper = reservaMapper;
    }
    /**
     * Cancela una reserva activa o pendiente.
     * <p>
     * Cambia el estado de la reserva a {@link EstadoReserva#CANCELADA} si es posible
     * y devuelve un {@link ReservaDTO} con los datos actualizados.
     * </p>
     *
     * @param idReserva ID de la reserva a cancelar
     * @return {@link ReservaDTO} con la información de la reserva cancelada
     * @throws ReservaNoEncontradaException   si no existe una reserva con el ID dado
     * @throws ReservaNoCancelableException   si la reserva ya está finalizada o cancelada
     */
    @Override
    @Transactional
    public ReservaDTO cancelarReserva(String idReserva) {

        // Buscar la reserva o lanzar excepción si no existe
        Reserva reserva = reservaRepo.findById(idReserva)
                .orElseThrow(() -> new ReservaNoEncontradaException("Reserva no encontrada."));

        // Validar si se puede cancelar
        if (reserva.getEstado() == EstadoReserva.FINALIZADA) {
            throw new ReservaNoCancelableException("No se puede cancelar una reserva finalizada.");
        }

        if (reserva.getEstado() == EstadoReserva.CANCELADA) {
            throw new ReservaNoCancelableException("La reserva ya está cancelada.");
        }

        // Cambiar estado a CANCELADA y guardar
        reserva.setEstado(EstadoReserva.CANCELADA);
        Reserva reservaActualizada = reservaRepo.save(reserva);

        // Mapear la entidad a DTO usando el mapper inyectado
        return reservaMapper.toDTO(reservaActualizada);
    }
}

