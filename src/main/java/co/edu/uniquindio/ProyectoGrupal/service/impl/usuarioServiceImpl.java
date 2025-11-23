package co.edu.uniquindio.ProyectoGrupal.service.impl;

import co.edu.uniquindio.ProyectoGrupal.Excepciones.ResourceNotFoundException;
import co.edu.uniquindio.ProyectoGrupal.Excepciones.ValueConflictException;
import co.edu.uniquindio.ProyectoGrupal.dtos.CrearUsuarioDTO;
import co.edu.uniquindio.ProyectoGrupal.mapper.UsuarioMapper;
import co.edu.uniquindio.ProyectoGrupal.model.Usuario;
import co.edu.uniquindio.ProyectoGrupal.repository.UsuarioRepository;
import co.edu.uniquindio.ProyectoGrupal.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class usuarioServiceImpl implements UsuarioService {


    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public void registrarUsuario(CrearUsuarioDTO usuarioDTO) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioDTO.correo());
        if (usuario.isPresent()) {
            throw new ValueConflictException("El usuario ya existe");
        }

        Usuario usuarioNuevo = usuarioMapper.toEntity(usuarioDTO);
        usuarioRepository.save(usuarioNuevo);

    }

    @Override
    public Usuario obtenerUsuario(String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent()) {
            return usuario.get();
        }
        throw new ResourceNotFoundException("El usuario no existe");
    }
}
