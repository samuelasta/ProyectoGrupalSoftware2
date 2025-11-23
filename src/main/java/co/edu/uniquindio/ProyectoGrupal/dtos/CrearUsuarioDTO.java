package co.edu.uniquindio.ProyectoGrupal.dtos;

import co.edu.uniquindio.ProyectoGrupal.model.enums.Rol;

import java.time.LocalDate;

public record CrearUsuarioDTO(String nombre,
                              String cedula,
                              String telefono,
                              String correo,
                              LocalDate fechaNacimiento,
                              Rol rol

                              ) {
}
