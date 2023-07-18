package server.bodyhealth.mapper;

import org.mapstruct.Mapper;
import server.bodyhealth.dto.ClienteRutinaCompletoDto;
import server.bodyhealth.dto.EjercicioCompletoDto;
import server.bodyhealth.entity.ClienteRutina;
import server.bodyhealth.entity.Ejercicio;

@Mapper(componentModel = "spring")
public interface ClienteRutinaCompletoMapper {
    ClienteRutina toEntity(ClienteRutinaCompletoDto clienteRutinaCompletoDto);
    ClienteRutinaCompletoDto toDto(ClienteRutina clienteRutina);

}
