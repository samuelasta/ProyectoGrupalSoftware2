package co.edu.uniquindio.ProyectoGrupal.mapper;

import co.edu.uniquindio.ProyectoGrupal.dtos.OpinionRequestDTO;
import co.edu.uniquindio.ProyectoGrupal.model.Alojamiento;
import co.edu.uniquindio.ProyectoGrupal.model.Comentario;
import co.edu.uniquindio.ProyectoGrupal.model.Reserva;
import co.edu.uniquindio.ProyectoGrupal.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
/**
 * Interfaz de mapeo para la conversión de un Objeto de Petición de Opinión
 * ({@link co.edu.uniquindio.ProyectoGrupal.dtos.OpinionRequestDTO}) a la entidad de persistencia
 * {@link co.edu.uniquindio.ProyectoGrupal.model.Comentario} (ya que una opinión es un Comentario en el modelo).
 * * Este mapper es fundamental para el registro de nuevas opiniones, ya que ensambla la entidad
 * a partir del DTO de entrada y los recursos relacionados ya cargados (Usuario, Alojamiento, Reserva).
 * * Utiliza MapStruct y la configuración para inyectar como un componente de Spring.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OpinionMapper {
    /**
     * Convierte el DTO de Petición de Opinión a la entidad {@code Comentario}.
     * * Requiere las entidades {@code Usuario}, {@code Alojamiento} y {@code Reserva} como
     * parámetros adicionales para establecer las relaciones de clave foránea.
     * * Genera un nuevo ID único para la entidad en el proceso.
     *
     * @param dto El DTO que contiene la calificación y el texto de la opinión.
     * @param usuario La entidad de Usuario ya recuperada, que emite la opinión.
     * @param alojamiento La entidad de Alojamiento ya recuperada, que recibe la opinión.
     * @param reserva La entidad de Reserva ya recuperada, opcionalmente asociada a la opinión.
     * @return La entidad de Comentario lista para ser persistida.
     */
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "comentario", source = "dto.comentario")
    @Mapping(target = "calificacion", source = "dto.calificacion")
    @Mapping(target = "usuario", source = "usuario")
    @Mapping(target = "alojamiento", source = "alojamiento")
    @Mapping(target = "reserva", source = "reserva")
    Comentario toEntity(OpinionRequestDTO dto, Usuario usuario, Alojamiento alojamiento, Reserva reserva);
}
