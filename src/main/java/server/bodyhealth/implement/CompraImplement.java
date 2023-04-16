package server.bodyhealth.implement;

import server.bodyhealth.dto.CompraCompletoDto;
import server.bodyhealth.dto.CompraDto;
import server.bodyhealth.entity.*;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.CompraCompletoMapper;
import server.bodyhealth.mapper.CompraMapper;
import server.bodyhealth.repository.CompraRepository;
import server.bodyhealth.repository.MetodoPagoRepository;
import server.bodyhealth.repository.UsuarioRepository;
import server.bodyhealth.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.bodyhealth.util.MessageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class CompraImplement implements CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private CompraMapper compraMapper;

    @Autowired
    private CompraCompletoMapper compraCompletoMapper;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<CompraDto> listarCompras() {
        List<CompraDto> compraDtos = new ArrayList<>();

        List<Compra> compras = compraRepository.findAll();

        if(!compras.isEmpty()){
            for (Compra compra: compras) {
                CompraDto compraDto = compraMapper.toDto(compra);
                compraDtos.add(compraDto);
            }
        } else {
            throw new NotFoundException(messageUtil.getMessage("compraEmpty",null, Locale.getDefault()));
        }

        return compraDtos;
    }

    @Override
    public int guardar(CompraDto compraDto) {

        Compra compra = compraMapper.toEntity(compraDto);
        compraRepository.save(compra);

        return compra.getId_compra();
    }

    @Override
    public void eliminar(int id) {

        Compra compra = compraRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("compraNotFound",null, Locale.getDefault()))
        );
        compraRepository.deleteById(id);

    }

    @Override
    public CompraCompletoDto encontrarCompra(int id_compra) {
        return compraCompletoMapper.toDto(compraRepository.findById(id_compra).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("compraNotFound",null, Locale.getDefault()))
        ));
    }

    @Override
    public CompraDto editarProveedor(int id, CompraDto compraDto) {

        Compra compra = compraRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("compraNotFound",null, Locale.getDefault()))
        );

        //compra.setId_compra(compraDto.getId_compra());

        if(compraDto.getFecha_compra()!=null)
            compra.setFecha_compra(compraDto.getFecha_compra());

        if(compraDto.getTotal() >1000)
            compra.setTotal(compraDto.getTotal());

        MetodoPago metodoPago = metodoPagoRepository.findById(compraDto.getMetodoPago().getId_metodopago()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("metodoPagoNotFound",null, Locale.getDefault()))
        );
        if(compraDto.getMetodoPago()!=null)
            compra.setMetodoPago(metodoPago);

        Usuario cliente = usuarioRepository.findById(compraDto.getCliente().getId_usuario()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("clienteNotFound",null, Locale.getDefault()))
        );
        if(compraDto.getCliente()!=null) {
            if(cliente.getRol().getId_rol() == 2){
                compra.setCliente(cliente);
            } else {
                throw new NotFoundException(messageUtil.getMessage("clienteDoesntExist",null, Locale.getDefault()));
            }

        }

        compraRepository.save(compra);

        return compraMapper.toDto(compra);
    }
}
