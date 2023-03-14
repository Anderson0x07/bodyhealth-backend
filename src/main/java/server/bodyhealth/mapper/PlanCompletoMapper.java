package server.bodyhealth.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import server.bodyhealth.dto.PlanCompletoDto;
import server.bodyhealth.entity.Plan;

@Mapper(componentModel = "spring")
public interface PlanCompletoMapper {

    Plan toEntity(PlanCompletoDto planCompletoDto);
    PlanCompletoDto toDto(Plan plan);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(PlanCompletoDto planCompletoDto, @MappingTarget Plan plan);
}
