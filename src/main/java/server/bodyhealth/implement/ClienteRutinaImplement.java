package server.bodyhealth.implement;

import server.bodyhealth.entity.ClienteRutina;
import server.bodyhealth.repository.ClienteRutinaRepository;
import server.bodyhealth.service.ClienteRutinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteRutinaImplement implements ClienteRutinaService {
    @Autowired
    private ClienteRutinaRepository clienteRutinaRepository;
    @Override
    public List<ClienteRutina> listarClientesRutina() {
        return (List<ClienteRutina>) clienteRutinaRepository.findAll();
    }

    @Override
    public void guardar(ClienteRutina clienteRutina) {
        clienteRutinaRepository.save(clienteRutina);
    }

    @Override
    public void eliminar(ClienteRutina clienteRutina) {
        clienteRutinaRepository.delete(clienteRutina);
    }

    @Override
    public ClienteRutina encontrarClienteRutina(int id_clienteRutina) {

        return clienteRutinaRepository.findById(id_clienteRutina).orElse(null);
    }

    @Override
    public List<ClienteRutina> encontrarClientesRutina(int id_rutina) {
        return clienteRutinaRepository.encontrarClientesRutina(id_rutina);
    }
}
