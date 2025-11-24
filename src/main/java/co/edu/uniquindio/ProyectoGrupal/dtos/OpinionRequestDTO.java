package co.edu.uniquindio.ProyectoGrupal.dtos;

public record OpinionRequestDTO( String usuarioId,
                                 String alojamientoId,
                                 String reservaId,
                                 int calificacion,
                                 String comentario) {
}
