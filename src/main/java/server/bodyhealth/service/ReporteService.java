package server.bodyhealth.service;

import org.springframework.stereotype.Service;
import server.bodyhealth.dto.ClienteDetalleDto;
import server.bodyhealth.dto.PedidoDto;

import java.util.List;

@Service
public interface ReporteService {

    public Object[] llenarReporteCompras(List<PedidoDto> pedidoDtos);

    public Object[] llenarReportePlan(ClienteDetalleDto clienteDetalleDto);


}
