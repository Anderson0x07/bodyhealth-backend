package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import server.bodyhealth.dto.ClienteDto;
import server.bodyhealth.service.ClienteService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cliente")
@CrossOrigin
@Slf4j
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    @GetMapping("/mi-perfil/{id_cliente}")
    public ResponseEntity<?> perfilCliente(@PathVariable int id_cliente){
        response.clear();
        response.put("cliente", clienteService.encontrarCliente(id_cliente));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> listarClientes(){
        response.clear();
        response.put("clientes",clienteService.listarClientes());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarCliente(ClienteDto clienteDto, @RequestPart(name = "file", required = false) MultipartFile file){
        response.clear();
        ClienteDto clienteDto1 = clienteService.loadImage(file,clienteDto);
        clienteDto.setPassword(bCryptPasswordEncoder.encode(clienteDto1.getPassword()));
        clienteService.guardar(clienteDto1);
        response.put("message", "Cliente guardado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarCliente(@PathVariable int id, ClienteDto clienteDto,@RequestPart(name = "file", required = false) MultipartFile file) {
        response.clear();
        ClienteDto clienteDto1 = clienteService.loadImage(file,clienteDto);
        clienteService.editarCliente(id,clienteDto1);
        response.put("message", "Datos actualizados satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable int id) {
        response.clear();
        clienteService.eliminar(id);
        response.put("message", "Cliente eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerClienteByID(@PathVariable int id) {
        response.clear();
        response.put("cliente", clienteService.encontrarCliente(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
