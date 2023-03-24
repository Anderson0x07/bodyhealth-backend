package server.bodyhealth.mapper;

import org.mapstruct.Mapper;
import server.bodyhealth.dto.ClienteRutinaEjercicioDto;
import server.bodyhealth.entity.ClienteRutinaEjercicio;

@Mapper(componentModel = "spring")
public interface ClienteRutinaEjercicioMapper {
    ClienteRutinaEjercicio toEntity(ClienteRutinaEjercicioDto clienteRutinaEjercicioDto);
    ClienteRutinaEjercicioDto toDto(ClienteRutinaEjercicio clienteRutinaEjercicio);
}
