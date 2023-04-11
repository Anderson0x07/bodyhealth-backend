package server.bodyhealth.mapper;

import org.mapstruct.Mapper;
import server.bodyhealth.dto.ClienteCompletoDto;
import server.bodyhealth.dto.UsuarioCompletoDto;
import server.bodyhealth.entity.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioCompletoMapper {

    Usuario toEntity(UsuarioCompletoDto usuarioCompletoDto);
    UsuarioCompletoDto toDto(Usuario usuario);
}
