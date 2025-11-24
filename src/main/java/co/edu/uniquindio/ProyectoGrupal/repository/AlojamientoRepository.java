package co.edu.uniquindio.ProyectoGrupal.repository;

import co.edu.uniquindio.ProyectoGrupal.model.Alojamiento;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repositorio Spring Data JPA para la entidad {@link co.edu.uniquindio.ProyectoGrupal.model.Alojamiento}.
 * * Esta interfaz proporciona automáticamente los métodos CRUD (Crear, Leer, Actualizar, Eliminar)
 * básicos, así como métodos para paginación y ordenamiento.
 * * La clase extiende {@code JpaRepository<Alojamiento, String>}, donde:
 * <ul>
 * <li>{@code Alojamiento} es el tipo de la entidad que se maneja.</li>
 * <li>{@code String} es el tipo de la clave primaria (ID) de la entidad {@code Alojamiento}.</li>
 * </ul>
 * * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 * @see co.edu.uniquindio.ProyectoGrupal.model.Alojamiento
 */
public interface AlojamientoRepository extends JpaRepository<Alojamiento, String> {
}
