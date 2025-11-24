package co.edu.uniquindio.ProyectoGrupal.service.impl;

import co.edu.uniquindio.ProyectoGrupal.Excepciones.UsuarioNoEncontradoException;
import co.edu.uniquindio.ProyectoGrupal.dtos.ReservaHistorialDTO;
import co.edu.uniquindio.ProyectoGrupal.mapper.ReservaHistorialMapper;
import co.edu.uniquindio.ProyectoGrupal.model.Reserva;
import co.edu.uniquindio.ProyectoGrupal.repository.ReservaRepository;
import co.edu.uniquindio.ProyectoGrupal.repository.UsuarioRepository;
import co.edu.uniquindio.ProyectoGrupal.service.HistorialService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
/**
 * Implementación del servicio de historial de reservas.
 * Permite obtener todas las reservas asociadas a un usuario específico.
 */
@Service
public class HistorialServiceImpl implements HistorialService {

    private final ReservaRepository reservaRepo;
    private final ReservaHistorialMapper reservaMapper;
    private final UsuarioRepository usuarioRepository;
    /**
     * Constructor con inyección de dependencias.
     *
     * @param reservaRepo       Repositorio de reservas.
     * @param reservaMapper     Mapper para convertir entidades Reserva a DTO.
     * @param usuarioRepository Repositorio de usuarios.
     */
    public HistorialServiceImpl(ReservaRepository reservaRepo,
                                ReservaHistorialMapper reservaMapper,
                                UsuarioRepository usuarioRepository) {
        this.reservaRepo = reservaRepo;
        this.reservaMapper = reservaMapper;
        this.usuarioRepository = usuarioRepository;
    }
    /**
     * Obtiene todas las reservas de un usuario dado.
     *
     * @param idUsuario Identificador del usuario.
     * @return Lista de {@link ReservaHistorialDTO} correspondientes al usuario.
     * @throws UsuarioNoEncontradoException Si el usuario no existe en la base de datos.
     */
    @Override
    public List<ReservaHistorialDTO> obtenerReservasUsuario(String idUsuario) {
        // Verificar que el usuario exista
        if (!usuarioRepository.existsById(idUsuario)) {
            throw new UsuarioNoEncontradoException("El usuario no existe");
        }

        // Obtener reservas
        List<Reserva> reservas = reservaRepo.findByUsuario_Id(idUsuario);

        // Mapear a DTO
        return reservas.stream()
                .map(reservaMapper::toDTO)
                .collect(Collectors.toList());
    }

}
