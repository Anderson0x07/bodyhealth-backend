package server.bodyhealth.service;

import server.bodyhealth.entity.Pedido;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PedidoService{
    public List<Pedido> listarPedidos();

    public void guardar(Pedido pedido);

    public void eliminar(Pedido pedido);

    public Pedido encontrarPedido(Pedido pedido);
}
