package server.bodyhealth.implement;

import server.bodyhealth.entity.ClienteDetalle;
import server.bodyhealth.repository.ClienteDetalleRepository;
import server.bodyhealth.service.ClienteDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteDetalleImplement implements ClienteDetalleService {
    @Autowired
    private ClienteDetalleRepository clienteDetalleRepository;
    @Override
    public List<ClienteDetalle> listarClientesDetalles() {
        return (List<ClienteDetalle>) clienteDetalleRepository.findAll();
    }
  
    @Override
    public void guardar(ClienteDetalle clienteDetalle) {
        clienteDetalleRepository.save(clienteDetalle);
    }

    @Override
    public void eliminar(ClienteDetalle clienteDetalle) {
        clienteDetalleRepository.delete(clienteDetalle);
    }

    @Override
    public ClienteDetalle encontrarClienteDetalle(ClienteDetalle clienteDetalle) {

        return clienteDetalleRepository.findById(clienteDetalle.getId_factura()).orElse(null);
    }
}
