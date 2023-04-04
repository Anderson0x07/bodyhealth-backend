package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.dto.MusculoDto;
import server.bodyhealth.service.MusculoService;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/musculo")
@CrossOrigin
@Slf4j
public class MusculoController {
    @Autowired
    private MusculoService musculoService;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_TRAINER')")
    @GetMapping("/all")
    public ResponseEntity<?> listarMusculos(){
        response.clear();
        response.put("musculos", musculoService.listarMusculos());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_TRAINER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerMusculo(@PathVariable int id) {
        response.clear();
        response.put("musculo", musculoService.encontrarMusculo(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_TRAINER')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarMusculo(@Valid @RequestBody MusculoDto musculoDto){
        response.clear();
        musculoService.guardar(musculoDto);
        response.put("message", "Musculo guardado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_TRAINER')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarMusculo(@PathVariable int id, @RequestBody MusculoDto musculoDto) {
        response.clear();
        MusculoDto musculo = musculoService.editarMusculo(id, musculoDto);
        response.put("message", "Musculo actualizado satisfactoriamente");
        response.put("musculo", musculo);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_TRAINER')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarMusculo(@PathVariable int id) {
        response.clear();
        musculoService.eliminar(id);
        response.put("message", "Musculo eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
