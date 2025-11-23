package co.edu.uniquindio.ProyectoGrupal.repository;

import co.edu.uniquindio.ProyectoGrupal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByEmail(String email);
}
