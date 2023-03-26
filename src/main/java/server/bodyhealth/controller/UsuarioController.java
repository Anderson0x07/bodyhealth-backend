package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.repository.UsuarioRepository;
import server.bodyhealth.service.EmailService;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private EmailService emailService;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("permitAll()")
    @GetMapping("/{documento}")
    public ResponseEntity<?> confirmarCorreo(@PathVariable int documento) {
        response.clear();
        emailService.confirmarCorreo(documento);
        response.put("message","Cuenta confirmada satisfactoriamente.");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}
