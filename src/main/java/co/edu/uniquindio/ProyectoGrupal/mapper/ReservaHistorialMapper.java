package co.edu.uniquindio.ProyectoGrupal.mapper;

import co.edu.uniquindio.ProyectoGrupal.dtos.ReservaHistorialDTO;
import co.edu.uniquindio.ProyectoGrupal.model.Reserva;
import org.mapstruct.Mapper;
/**
 * Interfaz de mapeo para la conversión de la entidad de persistencia
 * {@link co.edu.uniquindio.ProyectoGrupal.model.Reserva} a su objeto de transferencia de datos
 * {@link co.edu.uniquindio.ProyectoGrupal.dtos.ReservaHistorialDTO}.
 * * Este mapper se utiliza específicamente para preparar los datos de la reserva para
 * ser mostrados en la vista del historial del usuario.
 * * Utiliza la librería MapStruct, y la anotación {@code @Mapper} asegura que la
 * implementación generada sea un Spring Bean inyectable.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface ReservaHistorialMapper {
    /**
     * Convierte la entidad {@code Reserva} a su respectivo DTO de Historial.
     * * MapStruct realiza un mapeo automático de los campos que tienen nombres y tipos compatibles.
     * * Es vital que la entidad Reserva contenga todos los campos necesarios (id, checkIn, checkOut,
     * numeroHuespedes, estado, fechaCreacion) para que el mapeo sea completo.
     *
     * @param reserva La entidad de persistencia (modelo) de la Reserva.
     * @return El DTO de Historial de Reserva, listo para ser transferido al controlador
     * y luego al cliente.
     * @see co.edu.uniquindio.ProyectoGrupal.dtos.ReservaHistorialDTO
     */
    ReservaHistorialDTO toDTO(Reserva reserva);
}
