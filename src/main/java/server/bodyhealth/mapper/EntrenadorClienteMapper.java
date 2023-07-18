package server.bodyhealth.mapper;

import org.mapstruct.Mapper;
import server.bodyhealth.dto.EntrenadorClienteDto;
import server.bodyhealth.entity.EntrenadorCliente;

@Mapper(componentModel = "spring")
public interface EntrenadorClienteMapper {
    EntrenadorCliente toEntity(EntrenadorClienteDto entrenadorClienteDto);
    EntrenadorClienteDto toDto(EntrenadorCliente entrenadorCliente);
}
