package co.edu.uniquindio.ProyectoGrupal.model;

import co.edu.uniquindio.ProyectoGrupal.model.enums.Estado;
import co.edu.uniquindio.ProyectoGrupal.model.enums.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Usuario {

    @Id
    private String id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String cedula;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado;
}
