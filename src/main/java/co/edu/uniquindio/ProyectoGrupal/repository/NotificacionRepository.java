package co.edu.uniquindio.ProyectoGrupal.repository;


import co.edu.uniquindio.ProyectoGrupal.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repositorio Spring Data JPA para la entidad {@link co.edu.uniquindio.ProyectoGrupal.model.Notificacion}.
 * * Esta interfaz es responsable de la persistencia y la recuperación de todas las notificaciones
 * generadas en el sistema, generalmente asociadas a eventos como la confirmación de una reserva.
 * * Extiende {@code JpaRepository<Notificacion, String>}, lo que proporciona los métodos CRUD
 * estándar para la gestión de notificaciones.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 * @see co.edu.uniquindio.ProyectoGrupal.model.Notificacion
 */
@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, String> {
}
