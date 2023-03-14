package server.bodyhealth.mapper;

import org.mapstruct.*;
import server.bodyhealth.dto.ProveedorDto;
import server.bodyhealth.dto.RutinaDto;
import server.bodyhealth.entity.Proveedor;
import server.bodyhealth.entity.Rutina;

@Mapper(componentModel = "spring")
public interface RutinaMapper {
    Rutina toEntity(RutinaDto rutinaDto);
    RutinaDto toDto(Rutina rutina);
    @Mapping(target = "id_rutina", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(RutinaDto rutinaDto, @MappingTarget Rutina rutina);
}
