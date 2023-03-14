package server.bodyhealth.mapper;

import org.mapstruct.*;
import server.bodyhealth.dto.PlanDto;
import server.bodyhealth.entity.Plan;

@Mapper(componentModel = "spring")
public interface PlanMapper {
    Plan toEntity(PlanDto planDto);
    PlanDto toDto(Plan plan);
    @Mapping(target = "id_plan", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(PlanDto planDto, @MappingTarget Plan plan);
}
