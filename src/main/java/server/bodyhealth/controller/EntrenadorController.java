package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import server.bodyhealth.dto.EntrenadorDto;
import server.bodyhealth.dto.VerifyTokenRequestDto;
import server.bodyhealth.service.EmailService;
import server.bodyhealth.service.EntrenadorService;
import server.bodyhealth.service.EntrenadorService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/entrenador")
@CrossOrigin
@Slf4j
public class EntrenadorController {

    @Autowired
    private EntrenadorService entrenadorService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/mi-perfil/{id_entrenador}")
    public ResponseEntity<?> perfilEntrenador(@PathVariable int id_entrenador){
        response.clear();
        response.put("entrenador", entrenadorService.encontrarEntrenador(id_entrenador));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> listarEntrenadors(){
        response.clear();
        response.put("Entrenadores",entrenadorService.listarEntrenadores());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarEntrenador(@Valid @RequestBody EntrenadorDto entrenadorDto) throws IOException {
        response.clear();
        EntrenadorDto entrenadorDto1 = entrenadorService.loadImage(entrenadorDto);
        entrenadorDto.setPassword(bCryptPasswordEncoder.encode(entrenadorDto1.getPassword()));
        entrenadorService.guardar(entrenadorDto1);
        emailService.emailRegistro(entrenadorDto.getEmail(),entrenadorDto.getNombre(),entrenadorDto.getId_usuario());
        response.put("message", "Entrenador guardado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_TRAINER')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarEntrenador(@PathVariable int id,@RequestBody EntrenadorDto entrenadorDto) throws IOException {
        response.clear();
        EntrenadorDto entrenadorDto1 = entrenadorService.loadImage(entrenadorDto);
        EntrenadorDto entrenador = entrenadorService.editarEntrenador(id,entrenadorDto1);
        response.put("message", "Datos actualizados satisfactoriamente");
        response.put("entrenador", entrenador);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarEntrenador(@PathVariable int id) {
        response.clear();
        entrenadorService.eliminar(id);
        response.put("message", "Entrenador eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEntrenadorByID(@PathVariable int id) {
        response.clear();
        response.put("entrenador", entrenadorService.encontrarEntrenador(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_TRAINER')")
    @PostMapping("/restablecer-password/{id}")
    public ResponseEntity<?> restablecerPassword(@PathVariable int id) throws Exception {
        response.clear();
        entrenadorService.enviarTokenPassword(id);
        response.put("message", "Se envió el token al correo");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/verificar-token")
    public ResponseEntity<?> verifyToken(@RequestBody VerifyTokenRequestDto verifyTokenRequestDto) throws Exception {
        response.clear();
        entrenadorService.verificarToken(verifyTokenRequestDto);
        response.put("message", "Password actualizada satisfactoriamente.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
