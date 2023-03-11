package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.entity.Plan;
import server.bodyhealth.service.PlanService;

import java.util.List;

@RestController
@RequestMapping("/plan")
@CrossOrigin
@Slf4j
public class PlanController {
    @Autowired
    private PlanService planService;

    @GetMapping("/all")
    public ResponseEntity<List<Plan>> listarPlanes(){

        List<Plan> planes = planService.listarPlanes();
        if (!planes.isEmpty()) {
            return ResponseEntity.ok(planes);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id_plan}")
    public ResponseEntity<Plan> obtenerPlan(@PathVariable int id_plan) {
        Plan plan = planService.encontrarPlan(id_plan);
        if (plan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(plan);
    }

    @PostMapping("/guardar")
    public ResponseEntity<Plan> guardarPlan(@RequestBody Plan plan){
        Plan planExiste = planService.encontrarPlan(plan.getId_plan());
        if (planExiste == null) {
            planService.guardar(plan);
            return ResponseEntity.ok(plan);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/editar/{id_plan}")
    public ResponseEntity<Plan> editarPlan(@PathVariable int id_plan, @RequestBody Plan planActualizado) {
        Plan planExistente = planService.encontrarPlan(id_plan);
        if (planExistente != null) {

            planExistente.setMeses(planActualizado.getMeses());
            planExistente.setPlan(planActualizado.getPlan());
            planExistente.setPrecio(planActualizado.getPrecio());

            planService.guardar(planExistente);

            return ResponseEntity.ok(planExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id_plan}")
    public ResponseEntity<Void> eliminarPlan(@PathVariable int id_plan) {
        Plan planExistente = planService.encontrarPlan(id_plan);
        if (planExistente != null) {
            planService.eliminar(planExistente);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
