package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.dto.PlanDto;
import server.bodyhealth.entity.Plan;
import server.bodyhealth.service.PlanService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plan")
@CrossOrigin
@Slf4j
public class PlanController {
    @Autowired
    private PlanService planService;

    private Map<String,Object> response = new HashMap<>();

    @GetMapping("/all")
    public ResponseEntity<?> listarPlanes(){
        response.clear();
        response.put("Planes",planService.listarPlanes());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPlan(@PathVariable int id) {
        response.clear();
        response.put("Plan", planService.encontrarPlan(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarPlan(@Valid @RequestBody PlanDto planDto){
        response.clear();
        planService.guardar(planDto);
        response.put("message", "Plan guardado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarPlan(@PathVariable int id, @RequestBody PlanDto planDto) {
        response.clear();
        PlanDto plan = planService.editarPlan(id,planDto);
        response.put("message", "Plan actualizado satisfactoriamente");
        response.put("plan", plan);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPlan(@PathVariable int id) {
        response.clear();
        planService.eliminar(id);
        response.put("Message", "Plan eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
