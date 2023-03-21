package server.bodyhealth.mapper;

import org.mapstruct.Mapper;
import server.bodyhealth.dto.AdminDto;
import server.bodyhealth.entity.Usuario;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    Usuario toEntity(AdminDto adminDto);
    AdminDto toDto(Usuario usuario);
}
