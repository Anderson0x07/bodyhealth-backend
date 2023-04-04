package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.dto.ClienteRutinaEjercicioDto;
import server.bodyhealth.service.ClienteRutinaEjercicioService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/clienterutinaejercicio")
public class ClienteRutinaEjercicioController {
    @Autowired
    private ClienteRutinaEjercicioService clienteRutinaEjercicioService;

    private Map<String,Object> response = new HashMap<>();

    @GetMapping("/all")
    public ResponseEntity<?> listarClienteRutinaEjercicios(){
        response.clear();
        response.put("clienterutinaejercicios",clienteRutinaEjercicioService.listarClienteRutinaEjercicios());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerClienteRutinaEjercicioByID(@PathVariable int id) {
        response.clear();
        response.put("clienterutinaejercicio", clienteRutinaEjercicioService.encontrarClienteRutinaEjercicio(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarClienteRutinaEjercicio(@Valid @RequestBody ClienteRutinaEjercicioDto clienteRutinaEjercicioDto){
        response.clear();

        clienteRutinaEjercicioService.guardar(clienteRutinaEjercicioDto);
        response.put("message", "Cliente rutina ejercicio guardado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarClienteRutinaEjercicio(@PathVariable int id, @RequestBody ClienteRutinaEjercicioDto clienteRutinaEjercicioDto) {
        response.clear();
        ClienteRutinaEjercicioDto clienteRutinaEjercicio =  clienteRutinaEjercicioService.editarClienteRutinaEjercicio(id,clienteRutinaEjercicioDto);
        response.put("message", "Cliente rutina ejercicio actualizado satisfactoriamente");
        response.put("clienterutinaejercicio", clienteRutinaEjercicio);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarClienteRutinaEjercicio(@PathVariable int id) {
        response.clear();
        clienteRutinaEjercicioService.eliminar(id);
        response.put("message", "Cliente rutina ejercicio eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
