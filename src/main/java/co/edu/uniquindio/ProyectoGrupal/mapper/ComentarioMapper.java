package co.edu.uniquindio.ProyectoGrupal.mapper;

import co.edu.uniquindio.ProyectoGrupal.dtos.ComentarioDTO;
import co.edu.uniquindio.ProyectoGrupal.model.Comentario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {
    ComentarioDTO toDTO(Comentario comentario);
}
