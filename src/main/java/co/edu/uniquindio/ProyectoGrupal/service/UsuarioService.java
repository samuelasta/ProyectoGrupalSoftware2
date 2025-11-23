package co.edu.uniquindio.ProyectoGrupal.service;

import co.edu.uniquindio.ProyectoGrupal.dtos.CrearUsuarioDTO;
import co.edu.uniquindio.ProyectoGrupal.model.Usuario;

public interface UsuarioService {

    void registrarUsuario(CrearUsuarioDTO usuario);
    Usuario obtenerUsuario(String email);
}
