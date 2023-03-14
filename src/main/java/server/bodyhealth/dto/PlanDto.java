package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanDto {

    private int id_plan;
    @NotEmpty(message = "Se requiere un nombre para el plan")
    private String plan;
    @Positive(message = "El precio debe ser numero")
    private double precio;
    @Positive(message = "Los meses debe ser numero")
    private int meses;
}
