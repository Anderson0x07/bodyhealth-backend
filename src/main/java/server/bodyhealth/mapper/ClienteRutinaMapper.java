package server.bodyhealth.mapper;

import org.mapstruct.Mapper;
import server.bodyhealth.dto.ClienteRutinaDto;
import server.bodyhealth.dto.EjercicioDto;
import server.bodyhealth.entity.ClienteRutina;
import server.bodyhealth.entity.Ejercicio;

@Mapper(componentModel = "spring")
public interface ClienteRutinaMapper {
    ClienteRutina toEntity(ClienteRutinaDto clienteRutinaDto);
    ClienteRutinaDto toDto(ClienteRutina clienteRutina);
}
