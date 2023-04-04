package server.bodyhealth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.dto.MaquinaDto;
import server.bodyhealth.service.MaquinaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/maquina")
@CrossOrigin
@Slf4j
public class MaquinaController {
    @Autowired
    private MaquinaService maquinaService;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> listarMaquinas(){
        response.clear();
        response.put("maquinas",maquinaService.listarMaquinas());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/serial/{id_maquina}")
    public ResponseEntity<?> obtenerMaquinaBySerial(@PathVariable int id_maquina) {
        response.clear();
        response.put("maquina", maquinaService.encontrarMaquina(id_maquina));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerMaquinaByID(@PathVariable int id) {
        response.clear();
        response.put("maquina", maquinaService.encontrarMaquinaId(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarMaquina(@Valid @RequestBody MaquinaDto maquinaDto){
        response.clear();

        maquinaService.guardar(maquinaDto);
        response.put("message", "Maquina guardada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarMaquina(@PathVariable int id, @RequestBody MaquinaDto maquinaDto) {
        response.clear();
        MaquinaDto maquina = maquinaService.editarMaquina(id,maquinaDto);
        response.put("message", "Maquina actualizada satisfactoriamente");
        response.put("maquina", maquina);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarMaquina(@PathVariable int id) {
        response.clear();
        maquinaService.eliminar(id);
        response.put("message", "Maquina eliminada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
