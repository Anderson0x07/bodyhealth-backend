package server.bodyhealth.mapper;

import org.mapstruct.Mapper;
import server.bodyhealth.dto.ClienteDto;
import server.bodyhealth.entity.Usuario;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Usuario toEntity(ClienteDto clienteDto);
    ClienteDto toDto(Usuario usuario);
}
