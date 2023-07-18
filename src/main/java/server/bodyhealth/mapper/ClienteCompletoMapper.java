package server.bodyhealth.mapper;

import org.mapstruct.Mapper;
import server.bodyhealth.dto.ClienteCompletoDto;
import server.bodyhealth.entity.Usuario;

@Mapper(componentModel = "spring")
public interface ClienteCompletoMapper {

    Usuario toEntity(ClienteCompletoDto clienteCompletoDto);
    ClienteCompletoDto toDto(Usuario usuario);
}
