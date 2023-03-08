package server.bodyhealth.service;

import server.bodyhealth.entity.ClienteRutinaEjercicio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteRutinaEjercicioService {
    public List<ClienteRutinaEjercicio> listarClientesRutinaEjercicios();

    public void guardar(ClienteRutinaEjercicio clienteRutinaEjercicio);

    public void eliminar(ClienteRutinaEjercicio clienteRutinaEjercicio);

    public ClienteRutinaEjercicio encontrarClienteRutinaEjercicio(ClienteRutinaEjercicio clienteRutinaEjercicio);
}
