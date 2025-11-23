package co.edu.uniquindio.ProyectoGrupal.model;

import jakarta.persistence.*;

@Entity
public class Comentario {

    @Id
    private String id;

    @Column(nullable = false)
    private String comentario;

    @Column(nullable = false)
    private int calificacion;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Alojamiento alojamiento;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "reserva_id")
    private Reserva reserva;
}
