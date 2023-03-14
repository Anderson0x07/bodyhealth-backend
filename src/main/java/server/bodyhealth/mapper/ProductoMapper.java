package server.bodyhealth.mapper;

import org.mapstruct.*;
import server.bodyhealth.dto.ProductoDto;
import server.bodyhealth.dto.RutinaDto;
import server.bodyhealth.entity.Producto;
import server.bodyhealth.entity.Rutina;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    Producto toEntity(ProductoDto productoDto);
    ProductoDto toDto(Producto producto);
}
