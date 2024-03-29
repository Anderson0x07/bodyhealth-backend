package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RutinaDto {

    private int id_rutina;

    @NotEmpty(message = "Se requiere nombre para la rutina.")
    private String nombre_rutina;

    @NotEmpty(message = "Se requiere el nivel de la rutina.")
    private String nivel;

    @NotEmpty(message = "Se requiere la duracion de la rutina.")
    private String duracion;

    @NotEmpty(message = "Se requiere una descripcion de la rutina.")
    private String descripcion;
}
