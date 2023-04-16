package server.bodyhealth.implement;

import org.springframework.transaction.annotation.Transactional;
import server.bodyhealth.dto.PedidoDto;
import server.bodyhealth.entity.*;
import server.bodyhealth.entity.Pedido;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.PedidoMapper;
import server.bodyhealth.repository.CompraRepository;
import server.bodyhealth.repository.PedidoRepository;
import server.bodyhealth.repository.ProductoRepository;
import server.bodyhealth.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.bodyhealth.util.MessageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class PedidoImplement implements PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Autowired
    private MessageUtil messageUtil;

    @Override
    public List<PedidoDto> listarPedidos() {
        List<PedidoDto> pedidoDtos = new ArrayList<>();

        List<Pedido> pedidos = pedidoRepository.findAll();

        if(!pedidos.isEmpty()) {
            for (Pedido pedido : pedidos) {
                PedidoDto pedidoDto = pedidoMapper.toDto(pedido);
                pedidoDtos.add(pedidoDto);
            }
        }else{
            throw new NotFoundException(messageUtil.getMessage("pedidosEmpty",null, Locale.getDefault()));
        }

        return pedidoDtos;
    }

    @Transactional
    @Override
    public int guardar(PedidoDto pedidoDto) {
        Pedido pedido = pedidoMapper.toEntity(pedidoDto);


        compraRepository.findById(pedidoDto.getCompra().getId_compra()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("compraNotFound", null, Locale.getDefault()))
        );

        productoRepository.findById(pedidoDto.getProducto().getId_producto()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("productoNotFound", null, Locale.getDefault()))
        );

        pedidoRepository.save(pedido);

        return pedido.getId_pedido();
    }
    @Override
    public void eliminar(int id) {
        pedidoRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("pedidoNotFound",null, Locale.getDefault()))
        );
        pedidoRepository.deleteById(id);
    }

    @Transactional
    @Override
    public PedidoDto editarPedido(int id, PedidoDto pedidoDto) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("pedidoNotFound",null, Locale.getDefault()))
        );

        Compra compra = compraRepository.findById(pedidoDto.getCompra().getId_compra()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("compraNotFound", null, Locale.getDefault()))
        );
        if (pedidoDto.getCompra() != null)
            pedido.setCompra(compra);

        Producto producto = productoRepository.findById(pedidoDto.getProducto().getId_producto()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("productoNotFound", null, Locale.getDefault()))
        );
        if (pedidoDto.getProducto() != null)
            pedido.setProducto(producto);

        if (pedidoDto.getCantidad() != 0)
            pedido.setCantidad(pedidoDto.getCantidad());

        if (pedidoDto.getTotal() != 0)
            pedido.setTotal(pedidoDto.getTotal());

        pedidoRepository.save(pedido);
        return pedidoMapper.toDto(pedido);

    }

    @Override
    public PedidoDto encontrarPedido(int id_pedido) {

        return pedidoMapper.toDto(pedidoRepository.findById(id_pedido).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("pedidoNotFound",null, Locale.getDefault()))
        ));
    }

    @Override
    public List<PedidoDto> encontrarPedidoPorCompra(int id_compra) {

        List<PedidoDto> pedidoDtos = new ArrayList<>();

        List<Pedido> pedidos = pedidoRepository.findByCompra(id_compra);

        if(!pedidos.isEmpty()) {
            for (Pedido pedido : pedidos) {
                PedidoDto pedidoDto = pedidoMapper.toDto(pedido);
                pedidoDtos.add(pedidoDto);
            }
        }else{
            throw new NotFoundException(messageUtil.getMessage("pedidosEmpty",null, Locale.getDefault()));
        }

        return pedidoDtos;
    }
}
