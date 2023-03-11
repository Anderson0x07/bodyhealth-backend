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
        Plan newPlan = planService.encontrarPlan(plan.getId_plan());
        if (newPlan == null) {
            planService.guardar(plan);
            return ResponseEntity.ok(plan);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/editar/{id_plan}")
    public ResponseEntity<Plan> editarPlan(@PathVariable int id_plan, @RequestBody Plan planActualizado) {
        Plan plan = planService.encontrarPlan(id_plan);
        if (plan != null) {

            plan.setMeses(planActualizado.getMeses());
            plan.setPlan(planActualizado.getPlan());
            plan.setPrecio(planActualizado.getPrecio());

            planService.guardar(plan);

            return ResponseEntity.ok(plan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id_plan}")
    public ResponseEntity<Void> eliminarPlan(@PathVariable int id_plan) {
        Plan plan = planService.encontrarPlan(id_plan);
        if (plan != null) {
            planService.eliminar(plan);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
