package server.bodyhealth.mapper;

import org.mapstruct.Mapper;
import server.bodyhealth.dto.RolDto;
import server.bodyhealth.dto.RutinaCompletaDto;
import server.bodyhealth.entity.Rol;
import server.bodyhealth.entity.Rutina;

@Mapper(componentModel = "spring")
public interface RolMapper {

    Rol toEntity(RolDto rolDto);
    RolDto toDto(Rol rol);
}
