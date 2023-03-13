package server.bodyhealth.mapper;

import org.mapstruct.*;
import server.bodyhealth.dto.MaquinaDto;

import server.bodyhealth.entity.Maquina;

@Mapper(componentModel = "spring")
public interface MaquinaMapper {
    Maquina toEntity(MaquinaDto maquinaDto);
    MaquinaDto toDto(Maquina maquina);
//    @Mapping(target = "id", ignore = true)
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void updateEntity(MaquinaDto maquinaDto, @MappingTarget Maquina maquina);
}
