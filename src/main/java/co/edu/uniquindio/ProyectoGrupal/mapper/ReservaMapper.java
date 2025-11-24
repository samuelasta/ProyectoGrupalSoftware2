package co.edu.uniquindio.ProyectoGrupal.mapper;

import co.edu.uniquindio.ProyectoGrupal.dtos.ReservaDTO;
import co.edu.uniquindio.ProyectoGrupal.model.Reserva;
import org.mapstruct.Mapper;
/**
 * Interfaz de mapeo para la conversión de la entidad de persistencia
 * {@link co.edu.uniquindio.ProyectoGrupal.model.Reserva} a su objeto de transferencia de datos
 * {@link co.edu.uniquindio.ProyectoGrupal.dtos.ReservaDTO}.
 * * Este mapper se utiliza para casos donde solo se necesita transferir el ID y el estado
 * de la reserva, como en la respuesta a una operación de cancelación.
 * * Utiliza la librería MapStruct, y la anotación {@code @Mapper} asegura que la
 * implementación generada sea un Spring Bean inyectable.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface ReservaMapper {
    /**
     * Convierte la entidad {@code Reserva} a su DTO minimalista.
     * * MapStruct realiza un mapeo automático de los campos que tienen nombres y tipos compatibles,
     * en este caso, el ID y el estado.
     *
     * @param reserva La entidad de persistencia (modelo) de la Reserva.
     * @return El DTO de Reserva, que contiene el ID y el estado actual.
     * @see co.edu.uniquindio.ProyectoGrupal.dtos.ReservaDTO
     */
    ReservaDTO toDTO(Reserva reserva);
}
