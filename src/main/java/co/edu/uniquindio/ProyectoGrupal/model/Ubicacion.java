package co.edu.uniquindio.ProyectoGrupal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ubicacion {

    @Column(nullable = false)
    private String departamento;

    @Column(nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private String direccion;

}
