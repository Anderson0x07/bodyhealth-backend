package server.bodyhealth.service;

import server.bodyhealth.entity.ClienteRutina;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteRutinaService {
    public List<ClienteRutina> listarClientesRutina();

    public void guardar(ClienteRutina clienteRutina);

    public void eliminar(ClienteRutina clienteRutina);

    public ClienteRutina encontrarClienteRutina(ClienteRutina clienteRutina);

    public List<ClienteRutina> encontrarClientesRutina(int id_rutina);
}