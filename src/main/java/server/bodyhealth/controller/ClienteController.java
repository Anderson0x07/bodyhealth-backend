package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
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

    @PreAuthorize("hasRole('ROLE_USER')")
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
    public ResponseEntity<?> guardarCliente(@Valid @RequestBody ClienteDto clienteDto){
        response.clear();

        clienteDto.setPassword(bCryptPasswordEncoder.encode(clienteDto.getPassword()));

        clienteService.guardar(clienteDto);
        response.put("message", "Cliente guardado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarCliente(@PathVariable int id, @RequestBody ClienteDto clienteDto) {
        response.clear();
        clienteService.editarCliente(id,clienteDto);
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

}
