package server.bodyhealth.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.Double;

@Data
@NoArgsConstructor
public class FacturasPlanesDto {
    private String mes;
    private Long total_facturas;
    private Double total_ventas;

    public FacturasPlanesDto(String mes, Long total_facturas, Double total_ventas) {
        this.mes = mes;
        this.total_facturas = total_facturas;
        this.total_ventas = total_ventas;
    }

}
