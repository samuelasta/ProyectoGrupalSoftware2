package co.edu.uniquindio.ProyectoGrupal.mapper;

import co.edu.uniquindio.ProyectoGrupal.dtos.NotificacionDTO;
import co.edu.uniquindio.ProyectoGrupal.model.Notificacion;
import co.edu.uniquindio.ProyectoGrupal.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NotificacionMapper {

    @Mapping(target = "usuarioNombre", expression = "java(notificacion.getUsuario().getNombre())")
    @Mapping(target = "usuarioId", expression = "java(notificacion.getUsuario().getId())") // <-- agregado
    NotificacionDTO toDTO(Notificacion notificacion);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", source = "usuario")
    @Mapping(target = "estado", source = "dto.estado")
    Notificacion toEntity(NotificacionDTO dto, Usuario usuario);
}

