package server.bodyhealth.mapper;

import org.mapstruct.*;
import server.bodyhealth.dto.ClienteDto;
import server.bodyhealth.entity.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente toEntity(ClienteDto clienteDto);

    ClienteDto toDto(Cliente cliente);

    //@Mapping(target = "id_musculo", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(ClienteDto clienteDto, @MappingTarget Cliente cliente);
}
