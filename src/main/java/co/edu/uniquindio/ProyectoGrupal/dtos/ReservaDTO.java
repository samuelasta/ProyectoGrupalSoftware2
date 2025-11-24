package co.edu.uniquindio.ProyectoGrupal.dtos;

import co.edu.uniquindio.ProyectoGrupal.model.enums.EstadoReserva;

public record ReservaDTO(String id,
                         EstadoReserva estado) {
}
