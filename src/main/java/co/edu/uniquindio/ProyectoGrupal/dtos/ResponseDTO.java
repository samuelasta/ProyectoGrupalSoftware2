package co.edu.uniquindio.ProyectoGrupal.dtos;

public record ResponseDTO<T>(
        Boolean error, T mensaje
) {
}
