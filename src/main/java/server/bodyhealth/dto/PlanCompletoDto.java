package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import server.bodyhealth.entity.ClienteDetalle;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanCompletoDto {

    private int id_plan;
    @NotEmpty(message = "Se requiere un nombre para el plan")
    private String plan;
    @Positive
    private double precio;
    @Positive
    private int meses;

    private List<ClienteDetalle> clienteDetalles = new ArrayList<>();
}
