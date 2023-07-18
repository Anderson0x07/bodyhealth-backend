package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.dto.PlanDto;
import server.bodyhealth.service.PlanService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/plan")
@CrossOrigin
@Slf4j
public class PlanController {
    @Autowired
    private PlanService planService;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CLIENTE')")
    @GetMapping("/all")
    public ResponseEntity<?> listarPlanes(){
        response.clear();
        response.put("planes",planService.listarPlanes());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPlan(@PathVariable int id) {
        response.clear();
        response.put("plan", planService.encontrarPlan(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarPlan(@Valid @RequestBody PlanDto planDto){
        response.clear();
        planService.guardar(planDto);
        response.put("message", "Plan guardado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarPlan(@PathVariable int id, @RequestBody PlanDto planDto) {
        response.clear();
        PlanDto plan = planService.editarPlan(id,planDto);
        response.put("message", "Plan actualizado satisfactoriamente");
        response.put("plan", plan);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPlan(@PathVariable int id) {
        response.clear();
        planService.eliminar(id);
        response.put("Message", "Plan eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
