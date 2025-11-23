package co.edu.uniquindio.ProyectoGrupal.repository;

import co.edu.uniquindio.ProyectoGrupal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByCorreo(String email);
}
