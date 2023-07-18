package server.bodyhealth.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FacturasProductosDto {
    private String mes;
    private Double total_ventas;

    public FacturasProductosDto(String mes, Double total_ventas) {
        this.mes = mes;
        this.total_ventas = total_ventas;
    }

}
