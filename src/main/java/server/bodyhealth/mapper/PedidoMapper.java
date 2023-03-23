package server.bodyhealth.mapper;

import org.mapstruct.Mapper;
import server.bodyhealth.dto.PedidoDto;
import server.bodyhealth.entity.Pedido;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    Pedido toEntity(PedidoDto pedidoDto);
    PedidoDto toDto(Pedido pedido);
}
