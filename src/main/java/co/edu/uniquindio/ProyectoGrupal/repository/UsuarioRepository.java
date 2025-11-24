package co.edu.uniquindio.ProyectoGrupal.repository;

import co.edu.uniquindio.ProyectoGrupal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByCorreo(String email);
    /**
     * Verifica si existe una entidad {@code Usuario} con el ID dado en la base de datos.
     * * Este método es heredado de {@code JpaRepository} y está optimizado para
     * realizar una consulta eficiente (a menudo usando COUNT o EXISTS a nivel SQL)
     * sin cargar la entidad completa en memoria.
     *
     * @param id El identificador único (clave primaria) del usuario a verificar.
     * @return {@code true} si el usuario existe; {@code false} si no existe.
     */
    boolean existsById(String id);
}
