package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetodoPagoDto {

    private int id_metodopago;

    @NotEmpty(message = "Se requiere una descripción del método de pago")
    private String descripcion;
}
