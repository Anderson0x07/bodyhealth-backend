package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/all")
    public ResponseEntity<?> listarMetodoPago(){
        response.clear();
        response.put("Musculos", musculoService.listarMusculos());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerMusculo(@PathVariable int id) {
        response.clear();
        response.put("Musculo", musculoService.encontrarMusculo(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarMusculo(@Valid @RequestBody MusculoDto musculoDto){
        response.clear();
        musculoService.guardar(musculoDto);
        response.put("message", "Musculo guardado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarMusculo(@PathVariable int id, @RequestBody MusculoDto musculoDto) {
        response.clear();
        musculoService.editarMusculo(id, musculoDto);
        response.put("message", "Musculo actualizado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarMusculo(@PathVariable int id) {
        response.clear();
        musculoService.eliminar(id);
        response.put("message", "Musculo eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
