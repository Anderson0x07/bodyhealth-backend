package server.bodyhealth.service;

import org.springframework.stereotype.Service;
import server.bodyhealth.dto.PedidoDto;

import java.util.HashMap;
import java.util.List;

@Service
public interface ReporteService {

    public Object[] llenarReporte(List<PedidoDto> pedidoDtos);


}
