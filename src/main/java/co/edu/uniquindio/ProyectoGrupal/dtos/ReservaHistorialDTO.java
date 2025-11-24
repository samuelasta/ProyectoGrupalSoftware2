package co.edu.uniquindio.ProyectoGrupal.dtos;

import co.edu.uniquindio.ProyectoGrupal.model.enums.EstadoReserva;

import java.time.LocalDateTime;

public record ReservaHistorialDTO(String id,
                                  LocalDateTime checkIn,
                                  LocalDateTime checkOut,
                                  int numeroHuespedes,
                                  EstadoReserva estado,
                                  LocalDateTime fechaCreacion) {
}
