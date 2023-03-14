package server.bodyhealth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.dto.MetodoPagoDto;
import server.bodyhealth.service.MetodoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/metodopago")
@CrossOrigin
public class MetodoPagoController {
    @Autowired
    private MetodoPagoService metodoPagoService;

    private Map<String,Object> response = new HashMap<>();

    @GetMapping("/all")
    public ResponseEntity<?> listarMetodoPagoes(){
        response.clear();
        response.put("Metodos de pago", metodoPagoService.listarMetodosPago());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerMetodoPago(@PathVariable int id) {
        response.clear();
        response.put("Metodo de pago", metodoPagoService.encontrarMetodoPago(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarMetodoPago(@Valid @RequestBody MetodoPagoDto metodoPagoDto){
        response.clear();
        metodoPagoService.guardar(metodoPagoDto);
        response.put("message", "Metodo de pago guardado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarMetodoPago(@PathVariable int id, @RequestBody MetodoPagoDto metodoPagoDto) {
        response.clear();
        metodoPagoService.editarMetodoPago(id, metodoPagoDto);
        response.put("message", "Metodo de pago actualizado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarMetodoPago(@PathVariable int id) {
        response.clear();
        metodoPagoService.eliminar(id);
        response.put("message", "Metodo de pago eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
