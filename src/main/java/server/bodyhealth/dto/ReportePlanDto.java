package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ReportePlanDto {

    private String plan;
    private String tiempo;
    private String fechaInicio;
    private String fechaFin;


}
