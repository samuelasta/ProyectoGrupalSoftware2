package co.edu.uniquindio.ProyectoGrupal.model;

import co.edu.uniquindio.ProyectoGrupal.model.enums.Estado;

import java.time.LocalDateTime;

public class Reserva {

    private String id;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private int numeroHuespedes;
    private Estado estado;
    private LocalDateTime fechaCreacion;
    private Usuario usuario;
}
