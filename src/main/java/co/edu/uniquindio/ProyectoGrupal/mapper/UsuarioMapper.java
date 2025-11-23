package co.edu.uniquindio.ProyectoGrupal.mapper;

import co.edu.uniquindio.ProyectoGrupal.dtos.CrearUsuarioDTO;
import co.edu.uniquindio.ProyectoGrupal.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {


    @Mapping(target = "id", source = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "estado", constant = "ACTIVO")


    Usuario toEntity(CrearUsuarioDTO dto);
}
