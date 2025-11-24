package co.edu.uniquindio.ProyectoGrupal.service;

import co.edu.uniquindio.ProyectoGrupal.dtos.NotificacionDTO;
/**
 * Interfaz de servicio que define el contrato de negocio para el registro de reservas
 * y la generación de la notificación de confirmación asociada (CU10).
 * * La implementación de esta interfaz se encarga de la lógica transaccional que asegura
 * que una reserva sea persistida y que se genere su correspondiente registro de notificación.
 *
 * @author Daniel Eduardo Jurado Celemin
 * @version 1.0
 */
public interface NotificacionService {
    /**
     * Procesa la información de una reserva y, si es exitoso, genera un registro de notificación.
     * * Este método encapsula la lógica que valida los datos de la reserva y el usuario
     * antes de persistir la entidad de notificación.
     *
     * @param notificacionDTO El {@link co.edu.uniquindio.ProyectoGrupal.dtos.NotificacionDTO}
     * que contiene los detalles de la reserva a registrar y notificar.
     * @return El {@link co.edu.uniquindio.ProyectoGrupal.dtos.NotificacionDTO} con los datos
     * de la notificación de confirmación generada, incluyendo su ID.
     * @throws co.edu.uniquindio.ProyectoGrupal.Excepciones.UsuarioNoEncontradoException Si el ID de usuario
     * referenciado en el DTO no existe en el sistema.
     * @see co.edu.uniquindio.ProyectoGrupal.service.impl.NotificacionServiceImpl
     */
    NotificacionDTO registrarReserva(NotificacionDTO notificacionDTO);
}

