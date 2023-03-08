package server.bodyhealth.implement;

import server.bodyhealth.entity.ControlCliente;
import server.bodyhealth.repository.ControlClienteRepository;
import server.bodyhealth.service.ControlClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControlClienteImplement implements ControlClienteService {
    @Autowired
    private ControlClienteRepository controlClienteRepository;
    @Override
    public List<ControlCliente> listarContolClientes() {
        return (List<ControlCliente>) controlClienteRepository.findAll();
    }

    @Override
    public void guardar(ControlCliente controlCliente) {
        controlClienteRepository.save(controlCliente);
    }

    @Override
    public void eliminar(ControlCliente controlCliente) {
        controlClienteRepository.delete(controlCliente);
    }

    @Override
    public ControlCliente encontrarControlCliente(ControlCliente controCliente) {

        return controlClienteRepository.findById(controCliente.getId_controlcliente()).orElse(null);
    }
}
