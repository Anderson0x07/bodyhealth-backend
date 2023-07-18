package server.bodyhealth.mapper;

import org.mapstruct.Mapper;
import server.bodyhealth.dto.EntrenadorDto;
import server.bodyhealth.entity.Usuario;

@Mapper(componentModel = "spring")
public interface EntrenadorMapper {
    Usuario toEntity(EntrenadorDto entrenadorDto);
    EntrenadorDto toDto(Usuario usuario);
}
