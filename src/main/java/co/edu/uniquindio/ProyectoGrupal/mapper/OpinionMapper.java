package co.edu.uniquindio.ProyectoGrupal.mapper;

import co.edu.uniquindio.ProyectoGrupal.dtos.OpinionRequestDTO;
import co.edu.uniquindio.ProyectoGrupal.model.Alojamiento;
import co.edu.uniquindio.ProyectoGrupal.model.Comentario;
import co.edu.uniquindio.ProyectoGrupal.model.Reserva;
import co.edu.uniquindio.ProyectoGrupal.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OpinionMapper {

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "comentario", source = "dto.comentario")
    @Mapping(target = "calificacion", source = "dto.calificacion")
    @Mapping(target = "usuario", source = "usuario")
    @Mapping(target = "alojamiento", source = "alojamiento")
    @Mapping(target = "reserva", source = "reserva")
    Comentario toEntity(OpinionRequestDTO dto, Usuario usuario, Alojamiento alojamiento, Reserva reserva);
}
