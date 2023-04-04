package server.bodyhealth.implement;

import org.springframework.transaction.annotation.Transactional;
import server.bodyhealth.dto.PlanCompletoDto;
import server.bodyhealth.dto.PlanDto;
import server.bodyhealth.entity.Plan;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.PlanCompletoMapper;
import server.bodyhealth.mapper.PlanMapper;
import server.bodyhealth.repository.PlanRepository;
import server.bodyhealth.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.bodyhealth.util.MessageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class PlanImplement implements PlanService {

    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private PlanMapper planMapper;
    @Autowired
    private PlanCompletoMapper planCompletoMapper;
    @Autowired
    private MessageUtil messageUtil;

    @Override
    public List<PlanDto> listarPlanes() {
        List<PlanDto> planesDto = new ArrayList<>();
        for (Plan plan: planRepository.findAll()) {
            PlanDto planDto = planMapper.toDto(plan);
            planesDto.add(planDto);
        }
        return planesDto;
    }

    @Transactional
    @Override
    public void guardar(PlanDto planDto) {
        Plan plan = planMapper.toEntity(planDto);
        planRepository.save(plan);
    }

    @Override
    public void eliminar(int id_plan) {
        planRepository.findById(id_plan).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("planNotFound",null, Locale.getDefault()))
        );
        planRepository.deleteById(id_plan);
    }

    @Transactional
    @Override
    public PlanDto editarPlan(int id, PlanDto planDto) {
        Plan plan = planRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("planNotFound",null, Locale.getDefault()))
        );
        planMapper.updateEntity(planDto,plan);
        planRepository.save(plan);
        return planMapper.toDto(plan);
    }

    @Override
    public PlanCompletoDto encontrarPlan(int id_plan) {
        return planCompletoMapper.toDto(planRepository.findById(id_plan).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("planNotFound",null, Locale.getDefault()))
        ));
    }
}
