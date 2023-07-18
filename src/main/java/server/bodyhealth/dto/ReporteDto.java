package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ReporteDto {

    private int cantidad;
    private String nombre;
    private double precio;
    private double total;


}
