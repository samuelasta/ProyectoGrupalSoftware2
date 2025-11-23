package co.edu.uniquindio.ProyectoGrupal.model;

import co.edu.uniquindio.ProyectoGrupal.model.enums.Estado;
import co.edu.uniquindio.ProyectoGrupal.model.enums.Servicios;
import co.edu.uniquindio.ProyectoGrupal.model.enums.TipoAlojamiento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Alojamiento {

    @Id
    private String id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private int capacidad;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoAlojamiento tipoAlojamiento;

    @Column(nullable = false)
    private double precio;

    @Embedded
    @Column(nullable = false)
    private Ubicacion ubicacion;

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "alojamiento_id"))
    @Column(nullable = false, name = "servicio")
    private List<Servicios> servicios;

    @OneToMany(mappedBy = "alojamiento")
    private List<Comentario> comentarios;

}
