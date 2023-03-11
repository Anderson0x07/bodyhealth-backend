package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.entity.Entrenador;
import server.bodyhealth.service.UsuarioService;

import javax.validation.Valid;

@RestController
@CrossOrigin
@Slf4j
public class EntrenadorController {


    @Autowired
    private UsuarioService usuarioService;


    //M I P E R F I L
    @GetMapping("/trainer/mi-perfil/{prueba}")
    public ResponseEntity<Entrenador> perfilEntrenador(@PathVariable String prueba){
        Entrenador entrenador = usuarioService.encontrarEntrenadorEmail(prueba);
        if (entrenador == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entrenador);
    }


    @PutMapping("/trainer/perfil/editar")
    public ResponseEntity<Entrenador> editarPerfil(@Valid @RequestBody Entrenador entrenadorActualizado) {

        Entrenador entrenadorExistente = usuarioService.encontrarEntrenadorEmail(entrenadorActualizado.getEmail());

        if (entrenadorExistente != null) {

            entrenadorExistente.setNombre(entrenadorActualizado.getNombre());
            entrenadorExistente.setApellido(entrenadorActualizado.getApellido());
            entrenadorExistente.setDocumento(entrenadorActualizado.getDocumento());
            entrenadorExistente.setEmail(entrenadorActualizado.getEmail());
            entrenadorExistente.setTipo_documento(entrenadorActualizado.getTipo_documento());
            entrenadorExistente.setPassword(entrenadorActualizado.getPassword());
            entrenadorExistente.setFecha_nacimiento(entrenadorActualizado.getFecha_nacimiento());
            entrenadorExistente.setTelefono(entrenadorActualizado.getTelefono());
            entrenadorExistente.setFoto(entrenadorActualizado.getFoto());
            entrenadorExistente.setEstado(entrenadorActualizado.isEstado());
            entrenadorExistente.setExperiencia(entrenadorActualizado.getExperiencia());
            entrenadorExistente.setHoja_vida(entrenadorActualizado.getHoja_vida());
            entrenadorExistente.setJornada(entrenadorActualizado.getJornada());
            entrenadorExistente.setTitulo_academico(entrenadorActualizado.getTitulo_academico());

            usuarioService.guardar(entrenadorExistente);

            return ResponseEntity.ok(entrenadorExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
