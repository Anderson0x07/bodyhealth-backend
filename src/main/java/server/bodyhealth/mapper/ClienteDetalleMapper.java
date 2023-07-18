package server.bodyhealth.mapper;

import org.mapstruct.Mapper;
import server.bodyhealth.dto.ClienteDetalleDto;
import server.bodyhealth.entity.ClienteDetalle;

@Mapper(componentModel = "spring")
public interface ClienteDetalleMapper {
    ClienteDetalle toEntity(ClienteDetalleDto clienteDetalleDto);
    ClienteDetalleDto toDto(ClienteDetalle clienteDetalle);
}
