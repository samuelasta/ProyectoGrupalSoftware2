package co.edu.uniquindio.ProyectoGrupal.repository;

import co.edu.uniquindio.ProyectoGrupal.model.Reserva;
import co.edu.uniquindio.ProyectoGrupal.model.enums.EstadoReserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * Repositorio Spring Data JPA para la entidad {@link co.edu.uniquindio.ProyectoGrupal.model.Reserva}.
 * * Extiende {@code JpaRepository<Reserva, String>}, proporcionando los métodos CRUD estándar
 * para la gestión de reservas.
 * * Incluye métodos de consulta personalizados para filtrar reservas por usuario y estado.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 * @see co.edu.uniquindio.ProyectoGrupal.model.Reserva
 */
public interface ReservaRepository extends JpaRepository<Reserva, String> {

    List<Reserva> findByUsuarioIdAndEstado(String usuarioId, EstadoReserva estado);
    /**
     * Consulta personalizada que recupera todas las reservas asociadas a un usuario específico.
     * * Es útil para obtener el historial completo de reservas de un usuario.
     * * Se utiliza la convención de nombres para acceder a la propiedad ID dentro de la entidad
     * relacionada (Usuario).
     *
     * @param usuarioId El ID del usuario propietario de las reservas.
     * @return Una lista de objetos {@link co.edu.uniquindio.ProyectoGrupal.model.Reserva}.
     */
    List<Reserva> findByUsuario_Id(String usuarioId);
}
