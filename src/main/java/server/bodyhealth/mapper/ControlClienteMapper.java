package server.bodyhealth.mapper;

import org.mapstruct.Mapper;
import server.bodyhealth.dto.ControlClienteDto;
import server.bodyhealth.entity.ControlCliente;

@Mapper(componentModel = "spring")
public interface ControlClienteMapper {

    ControlCliente toEntity(ControlClienteDto controlClienteDto);
    ControlClienteDto toDto(ControlCliente controlCliente);
}
