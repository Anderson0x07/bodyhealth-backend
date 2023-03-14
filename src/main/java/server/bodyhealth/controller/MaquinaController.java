package server.bodyhealth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.dto.MaquinaDto;
import server.bodyhealth.entity.Maquina;
import server.bodyhealth.service.MaquinaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/maquina")
@CrossOrigin
@Slf4j
public class MaquinaController {
    @Autowired
    private MaquinaService maquinaService;

    private Map<String,Object> response = new HashMap<>();

    @GetMapping("/all")
    public ResponseEntity<?> listarMaquinas(){
        response.clear();
        response.put("maquina",maquinaService.listarMaquinas());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/serial/{id_maquina}")
    public ResponseEntity<?> obtenerMaquinaBySerial(@PathVariable int id_maquina) {
        response.clear();
        response.put("maquina", maquinaService.encontrarMaquina(id_maquina));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerMaquinaByID(@PathVariable int id) {
        response.clear();
        response.put("maquina", maquinaService.encontrarMaquinaId(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarMaquina(@Valid @RequestBody MaquinaDto maquinaDto){
        response.clear();
        maquinaService.guardar(maquinaDto);
        response.put("message", "Maquina guardado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarMaquina(@PathVariable int id, @RequestBody MaquinaDto maquinaDto) {
        response.clear();
        maquinaService.editarMaquina(id,maquinaDto);
        response.put("message", "Maquina actualizada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarMaquina(@PathVariable int id) {
        response.clear();
        maquinaService.eliminar(id);
        response.put("message", "Maquina eliminada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
