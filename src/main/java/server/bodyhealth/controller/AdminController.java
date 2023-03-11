package server.bodyhealth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.entity.Administrador;
import server.bodyhealth.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;

@RestController
@Slf4j
public class AdminController {
    @Autowired
    private UsuarioService usuarioService;


    //M I P E R F I L
    @GetMapping("/admin/mi-perfil/{prueba}")
    public ResponseEntity<Administrador> perfilAdmin(@PathVariable String prueba){
        Administrador administrador = usuarioService.encontrarAdminEmail(prueba);
        if (administrador == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(administrador);
    }


    @PutMapping("/admin/perfil/editar")
    public ResponseEntity<Administrador> editarPerfil(@Valid @RequestBody Administrador administradorActualizado) {

        Administrador administradorExistente = usuarioService.encontrarAdminEmail(administradorActualizado.getEmail());

        if (administradorExistente != null) {

            administradorExistente.setNombre(administradorActualizado.getNombre());
            administradorExistente.setApellido(administradorActualizado.getApellido());
            administradorExistente.setDocumento(administradorActualizado.getDocumento());
            administradorExistente.setEmail(administradorActualizado.getEmail());
            administradorExistente.setTipo_documento(administradorActualizado.getTipo_documento());
            administradorExistente.setPassword(administradorActualizado.getPassword());
            administradorExistente.setFecha_nacimiento(administradorActualizado.getFecha_nacimiento());
            administradorExistente.setTelefono(administradorActualizado.getTelefono());

            usuarioService.guardar(administradorExistente);
            // Devolver una respuesta exitosa con el admin actualizado
            return ResponseEntity.ok(administradorExistente);
        } else {
            // Devolver una respuesta de error si el admin no existe
            return ResponseEntity.notFound().build();
        }
    }

}