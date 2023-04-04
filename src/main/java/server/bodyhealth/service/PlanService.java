package server.bodyhealth.service;

import org.springframework.stereotype.Service;
import server.bodyhealth.dto.PlanCompletoDto;
import server.bodyhealth.dto.PlanDto;
import server.bodyhealth.dto.ProveedorCompletoDto;
import server.bodyhealth.dto.ProveedorDto;
import server.bodyhealth.entity.Plan;

import java.util.List;

@Service
public interface PlanService {
    public List<PlanDto> listarPlanes();

    public void guardar(PlanDto planDto);

    public void eliminar(int id_plan);

    public PlanDto editarPlan(int id, PlanDto planDto);

    public PlanCompletoDto encontrarPlan(int id_plan);

}
