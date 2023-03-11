package server.bodyhealth.service;

import org.springframework.stereotype.Service;
import server.bodyhealth.entity.Plan;

import java.util.List;

@Service
public interface PlanService {
    public List<Plan> listarPlanes();

    public void guardar(Plan plan);

    public void eliminar(Plan plan);

    public Plan encontrarPlan(int plan);
}
