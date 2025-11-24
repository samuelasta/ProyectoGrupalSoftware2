package co.edu.uniquindio.ProyectoGrupal.repository;

import co.edu.uniquindio.ProyectoGrupal.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * Repositorio Spring Data JPA para la entidad {@link co.edu.uniquindio.ProyectoGrupal.model.Comentario}.
 * * Extiende {@code JpaRepository<Comentario, String>}, lo que proporciona los métodos CRUD
 * estándar para la gestión de comentarios.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 * @see co.edu.uniquindio.ProyectoGrupal.model.Comentario
 */
public interface ComentarioRepository extends JpaRepository<Comentario, String> {
    /**
     * Consulta personalizada generada automáticamente por Spring Data JPA.
     * * Recupera una lista de comentarios asociados a un alojamiento específico utilizando
     * el ID del alojamiento.
     * * El nombre del método sigue la convención de Spring Data: {@code findBy[Propiedad]_[PropiedadAnidada]}.
     *
     * @param idAlojamiento El ID del alojamiento cuyos comentarios se desean obtener.
     * @return Una lista de objetos {@link co.edu.uniquindio.ProyectoGrupal.model.Comentario} asociados
     * al alojamiento. Si no existen comentarios, retorna una lista vacía.
     */
    List<Comentario> findByAlojamiento_Id(String idAlojamiento);
}
