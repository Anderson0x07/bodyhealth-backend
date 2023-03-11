package server.bodyhealth.implement;

import server.bodyhealth.entity.Plan;
import server.bodyhealth.repository.PlanRepository;
import server.bodyhealth.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanImplement implements PlanService {

    @Autowired
    private PlanRepository planRepository;

    @Override
    public List<Plan> listarPlanes() {
        return planRepository.findAll();
    }

    @Override
    public void guardar(Plan plan) {
        planRepository.save(plan);
    }

    @Override
    public void eliminar(Plan plan) {
        planRepository.delete(plan);
    }

    @Override
    public Plan encontrarPlan(int id_plan) {
        return planRepository.findById(id_plan).orElse(null);
    }
}
