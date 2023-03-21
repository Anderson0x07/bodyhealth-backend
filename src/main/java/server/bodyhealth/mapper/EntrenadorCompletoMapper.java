package server.bodyhealth.mapper;

import org.mapstruct.Mapper;
import server.bodyhealth.dto.EntrenadorCompletoDto;
import server.bodyhealth.dto.EntrenadorDto;
import server.bodyhealth.entity.Usuario;

@Mapper(componentModel = "spring")
public interface EntrenadorCompletoMapper {
    Usuario toEntity(EntrenadorCompletoDto entrenadorCompletoDto);
    EntrenadorCompletoDto toDto(Usuario usuario);

}
