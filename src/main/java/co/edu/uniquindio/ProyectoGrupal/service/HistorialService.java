package co.edu.uniquindio.ProyectoGrupal.service;

import co.edu.uniquindio.ProyectoGrupal.dtos.ReservaHistorialDTO;

import java.util.List;

public interface HistorialService {
    List<ReservaHistorialDTO> obtenerReservasUsuario(String idUsuario);
}
