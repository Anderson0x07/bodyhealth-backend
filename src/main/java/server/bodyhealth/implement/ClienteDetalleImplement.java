package server.bodyhealth.implement;

import org.springframework.transaction.annotation.Transactional;
import server.bodyhealth.dto.ClienteDetalleDto;
import server.bodyhealth.entity.*;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.ClienteDetalleMapper;
import server.bodyhealth.repository.*;
import server.bodyhealth.service.ClienteDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.bodyhealth.util.MessageUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ClienteDetalleImplement implements ClienteDetalleService {
    @Autowired
    private ClienteDetalleRepository clienteDetalleRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private ClienteDetalleMapper clienteDetalleMapper;


    @Override
    public List<ClienteDetalleDto> listarClientesDetalles() {
        List<ClienteDetalleDto> clienteDetallesDto = new ArrayList<>();

        List<ClienteDetalle> clienteDetalles = clienteDetalleRepository.findAll();

        if(!clienteDetalles.isEmpty()) {
            for (ClienteDetalle clienteDetalle : clienteDetalles) {
                ClienteDetalleDto clienteDetalleDto = clienteDetalleMapper.toDto(clienteDetalle);
                clienteDetallesDto.add(clienteDetalleDto);
            }
        }else{
            throw new NotFoundException(messageUtil.getMessage("clienteDetallesEmpty",null, Locale.getDefault()));
        }
        return clienteDetallesDto;

    }

    @Transactional
    @Override
    public int guardar(ClienteDetalleDto clienteDetalleDto) {
        ClienteDetalle clienteDetalle = clienteDetalleMapper.toEntity(clienteDetalleDto);

        planRepository.findById(clienteDetalleDto.getPlan().getId_plan()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("planNotFound", null, Locale.getDefault()))
        );

        Usuario cliente = usuarioRepository.findById(clienteDetalleDto.getCliente().getId_usuario()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("clienteNotFound", null, Locale.getDefault()))
        );
        if(cliente.getRol().getId_rol() != 2){
            throw new NotFoundException(messageUtil.getMessage("clienteDoesntExist",null, Locale.getDefault()));
        }

        metodoPagoRepository.findById(clienteDetalleDto.getMetodoPago().getId_metodopago()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("metodoPagoNotFound", null, Locale.getDefault()))
        );

        clienteDetalleRepository.save(clienteDetalle);

        return clienteDetalle.getId_factura();
    }
    @Override
    public void eliminar(int id) {
        clienteDetalleRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("clienteDetalleNotFound",null, Locale.getDefault()))
        );
        clienteDetalleRepository.deleteById(id);
    }

    @Transactional
    @Override
    public ClienteDetalleDto editarClienteDetalle(int id, ClienteDetalleDto clienteDetalleDto) {
        ClienteDetalle clienteDetalle = clienteDetalleRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("clienteDetalleNotFound",null, Locale.getDefault()))
        );


        Usuario cliente = usuarioRepository.findById(clienteDetalleDto.getCliente().getId_usuario()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("clienteNotFound", null, Locale.getDefault()))
        );
        if(clienteDetalleDto.getCliente()!=null){
            if(cliente.getRol().getId_rol() == 2){
                clienteDetalle.setCliente(cliente);
            }else{
                throw new NotFoundException(messageUtil.getMessage("clienteDoesntExist",null, Locale.getDefault()));
            }
        }

        MetodoPago metodoPago = metodoPagoRepository.findById(clienteDetalleDto.getMetodoPago().getId_metodopago()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("metodoPagoNotFound", null, Locale.getDefault()))
        );
        if (clienteDetalleDto.getMetodoPago() != null)
            clienteDetalle.setMetodoPago(metodoPago);

        Plan plan = planRepository.findById(clienteDetalleDto.getPlan().getId_plan()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("planNotFound", null, Locale.getDefault()))
        );
        if (clienteDetalleDto.getPlan() != null)
            clienteDetalle.setPlan(plan);

        if (clienteDetalleDto.getFecha_inicio() != null)
            clienteDetalle.setFecha_inicio(clienteDetalleDto.getFecha_inicio());

        if (clienteDetalleDto.getFecha_fin() != null)
            clienteDetalle.setFecha_fin(clienteDetalleDto.getFecha_fin());


        clienteDetalleRepository.save(clienteDetalle);

        return clienteDetalleMapper.toDto(clienteDetalle);

    }

    @Override
    public ClienteDetalleDto encontrarClienteDetalle(int id_clienteDetalle) {

        return clienteDetalleMapper.toDto(clienteDetalleRepository.findById(id_clienteDetalle).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("clienteDetalleNotFound",null, Locale.getDefault()))
        ));
    }
}
