package server.bodyhealth.service;

import server.bodyhealth.dto.ClienteDetalleDto;
import server.bodyhealth.dto.PedidoDto;
import server.bodyhealth.entity.Pedido;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PedidoService{
    public List<PedidoDto> listarPedidos();

    public int guardar(PedidoDto pedidoDto);

    public void eliminar(int id_pedido);

    public PedidoDto editarPedido(int id, PedidoDto pedidoDto);

    public PedidoDto encontrarPedido(int id_pedido);

    public List<PedidoDto> encontrarPedidoPorCompra(int id_compra);
}
