package co.edu.uniquindio.ProyectoGrupal.mapper;

import co.edu.uniquindio.ProyectoGrupal.dtos.ComentarioDTO;
import co.edu.uniquindio.ProyectoGrupal.model.Comentario;
import org.mapstruct.Mapper;
/**
 * Interfaz de mapeo para la conversión de objetos entre la entidad de persistencia
 * {@link co.edu.uniquindio.ProyectoGrupal.model.Comentario} y su objeto de transferencia de datos
 * {@link co.edu.uniquindio.ProyectoGrupal.dtos.ComentarioDTO}.
 * * Esta interfaz utiliza la librería MapStruct, y la anotación {@code @Mapper} genera
 * automáticamente el código de implementación en tiempo de compilación.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface ComentarioMapper {
    /**
     * Convierte una entidad {@code Comentario} a su respectivo DTO.
     * * Este método mapea automáticamente los campos de igual nombre y tipo.
     * * Nota: MapStruct se encarga de la lógica compleja, como mapear campos anidados
     * (ej. el nombre del usuario desde la entidad de usuario) si las anotaciones
     * en la implementación de la entidad lo permiten.
     *
     * @param comentario La entidad de persistencia (modelo) de Comentario.
     * @return El DTO de Comentario, listo para ser transferido a la capa de presentación.
     * @see co.edu.uniquindio.ProyectoGrupal.dtos.ComentarioDTO
     */
    ComentarioDTO toDTO(Comentario comentario);
}
