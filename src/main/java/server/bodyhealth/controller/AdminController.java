package server.bodyhealth.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import server.bodyhealth.entity.Administrador;
import server.bodyhealth.repository.UsuarioRepository;
import server.bodyhealth.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
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
    public ResponseEntity<Administrador> editarPerfil(@Valid @RequestBody Administrador administrador) {

        Administrador newAdministrador = usuarioService.encontrarAdminEmail(administrador.getEmail());

        if (newAdministrador != null) {

            newAdministrador.setId_usuario(administrador.getId_usuario());

            newAdministrador.setNombre(administrador.getNombre());
            newAdministrador.setApellido(administrador.getApellido());
            newAdministrador.setDocumento(administrador.getDocumento());
            newAdministrador.setEmail(administrador.getEmail());
            newAdministrador.setTipo_documento(administrador.getTipo_documento());
            newAdministrador.setPassword(administrador.getPassword());
            newAdministrador.setFecha_nacimiento(administrador.getFecha_nacimiento());
            newAdministrador.setTelefono(administrador.getTelefono());

            usuarioService.guardar(administrador);
            // Devolver una respuesta exitosa con el admin actualizado
            return new ResponseEntity<>(administrador, HttpStatus.OK);
        } else {
            // Devolver una respuesta de error si el admin no existe
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}