package co.edu.uniquindio.ProyectoGrupal.model;

import co.edu.uniquindio.ProyectoGrupal.model.enums.Estado;
import co.edu.uniquindio.ProyectoGrupal.model.enums.Rol;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Usuario {

    private String id;
    private String nombre;
    private String cedula;
    private String telefono;
    private String correo;
    private LocalDate fechaNacimiento;
    private Rol rol;
    private Estado estado;
}
