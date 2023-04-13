package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.dto.InfoBasicaDto;
import server.bodyhealth.service.InfoBasicaService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/infobasica")
@CrossOrigin
@Slf4j
public class InfoBasicaController {

    @Autowired
    private InfoBasicaService infoBasicaService;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerInfoBasica(@PathVariable int id) {
        response.clear();
        response.put("infobasica", infoBasicaService.encontrarInfoBasica(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarInfoBasica(@Valid @RequestBody InfoBasicaDto infoBasicaDto){
        response.clear();
        infoBasicaService.guardar(infoBasicaDto);
        response.put("message", "Informacion basica guardada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> actualizarInfoBasica(@PathVariable int id, @RequestBody InfoBasicaDto infoBasicaDto) throws IOException {
        response.clear();
        InfoBasicaDto infoBasicaDto1 = infoBasicaService.loadImage(infoBasicaDto);
        InfoBasicaDto infoBasica = infoBasicaService.editarInfoBasica(id, infoBasicaDto1);
        response.put("message", "Informacion basica actualizada satisfactoriamente");
        response.put("infobasica", infoBasica);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarInfoBasica(@PathVariable int id) {
        response.clear();
        infoBasicaService.eliminar(id);
        response.put("message", "Informacion basica eliminada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_TRAINER') OR hasRole('ROLE_CLIENTE')")
    @GetMapping("/logo/{id_configuracion}")
    public ResponseEntity<?> obtenerLogoInfoBasica(@PathVariable int id_configuracion) {
        response.clear();
        response.put("logo", infoBasicaService.encontrarLogo(id_configuracion));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
