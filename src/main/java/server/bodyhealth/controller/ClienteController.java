package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.entity.Cliente;
import server.bodyhealth.service.UsuarioService;

import javax.validation.Valid;

@RestController
@CrossOrigin
@Slf4j
public class ClienteController {

    @Autowired
    private UsuarioService usuarioService;


    //M I P E R F I L
    @GetMapping("/cliente/mi-perfil/{prueba}")
    public ResponseEntity<Cliente> perfilCliente(@PathVariable String prueba){
        Cliente cliente = usuarioService.encontrarClienteEmail(prueba);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }


    @PutMapping("/cliente/perfil/editar")
    public ResponseEntity<Cliente> editarPerfil(@Valid @RequestBody Cliente clienteActualizado) {

        Cliente clienteExistente = usuarioService.encontrarClienteEmail(clienteActualizado.getEmail());

        if (clienteExistente != null) {

            clienteExistente.setNombre(clienteActualizado.getNombre());
            clienteExistente.setApellido(clienteActualizado.getApellido());
            clienteExistente.setDocumento(clienteActualizado.getDocumento());
            clienteExistente.setEmail(clienteActualizado.getEmail());
            clienteExistente.setTipo_documento(clienteActualizado.getTipo_documento());
            clienteExistente.setPassword(clienteActualizado.getPassword());
            clienteExistente.setFecha_nacimiento(clienteActualizado.getFecha_nacimiento());
            clienteExistente.setTelefono(clienteActualizado.getTelefono());
            clienteExistente.setJornada(clienteActualizado.getJornada());
            clienteExistente.setComentario(clienteActualizado.getComentario());
            clienteExistente.setEstado(clienteActualizado.isEstado());
            clienteExistente.setFoto(clienteActualizado.getFoto());

            usuarioService.guardar(clienteExistente);

            return ResponseEntity.ok(clienteExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
