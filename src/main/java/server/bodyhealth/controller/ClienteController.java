package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import server.bodyhealth.dto.ClienteDto;
import server.bodyhealth.dto.VerifyTokenRequestDto;
import server.bodyhealth.service.ClienteService;
import server.bodyhealth.service.EmailService;

import javax.annotation.security.PermitAll;
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

    @Autowired
    private EmailService emailService;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CLIENTE')")
    @GetMapping("/mi-perfil/{id_cliente}")
    public ResponseEntity<?> perfilCliente(@PathVariable int id_cliente){
        response.clear();
        response.put("cliente", clienteService.encontrarCliente(id_cliente));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PermitAll
    @GetMapping("/all")
    public ResponseEntity<?> listarClientes(){
        response.clear();
        response.put("clientes",clienteService.listarClientes());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PermitAll
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarCliente(@Valid @RequestBody ClienteDto clienteDto) throws Exception {
        response.clear();
        ClienteDto clienteDto1 =  clienteService.loadImage(clienteDto);
        clienteDto.setPassword(bCryptPasswordEncoder.encode(clienteDto1.getPassword()));
        clienteService.guardar(clienteDto1);
        emailService.emailRegistro(clienteDto1.getEmail(),clienteDto1.getNombre(),clienteDto1.getDocumento());
        response.put("message", "Cliente guardado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CLIENTE')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarCliente(@PathVariable int id, @RequestBody ClienteDto clienteDto) throws Exception {
        response.clear();
        ClienteDto clienteDto1 = clienteService.loadImage(clienteDto);
        ClienteDto cliente =  clienteService.editarCliente(id,clienteDto1);
        response.put("message", "Datos actualizados satisfactoriamente");
        response.put("cliente", cliente);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CLIENTE')")
    @PutMapping("/cambiar-estado/{id}/{estado}")
    public ResponseEntity<?> cambiarEstadoCliente(@PathVariable int id, @PathVariable boolean estado) {
        response.clear();
        clienteService.editarEstado(id,estado);
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

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_TRAINER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerClienteByID(@PathVariable int id) {
        response.clear();
        response.put("cliente", clienteService.encontrarCliente(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PermitAll
    @PostMapping("/restablecer-password/{id}")
    public ResponseEntity<?> restablecerPassword(@PathVariable int id) throws Exception {
        response.clear();
        clienteService.enviarTokenPassword(id);
        response.put("message", "Se envió el token al correo");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PermitAll
    @PostMapping("/verificar-token")
    public ResponseEntity<?> verifyToken(@RequestBody VerifyTokenRequestDto verifyTokenRequestDto) throws Exception {
        response.clear();
        clienteService.verificarToken(verifyTokenRequestDto);
        response.put("message", "Password actualizada satisfactoriamente.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
