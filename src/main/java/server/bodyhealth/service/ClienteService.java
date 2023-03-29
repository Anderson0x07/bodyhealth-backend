package server.bodyhealth.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import server.bodyhealth.dto.ClienteCompletoDto;
import server.bodyhealth.dto.ClienteDto;
import server.bodyhealth.dto.ProductoCompletoDto;
import server.bodyhealth.dto.ProductoDto;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public interface ClienteService {

    public List<ClienteDto> listarClientes();

    public void guardar(ClienteDto clienteDto);

    public void eliminar(int id_cliente);

    public ClienteCompletoDto encontrarCliente(int id_cliente);

    public ClienteCompletoDto encontrarClienteByDocument(int documento);

    public void editarCliente(int id, ClienteDto clienteDto);

    public ClienteDto loadImage(ClienteDto clienteDto) throws IOException;
}
