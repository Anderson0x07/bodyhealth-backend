package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RutinaCompletaSinClienteDto {
    private int id_rutina;

    @NotEmpty(message = "Se requiere nombre para la rutina.")
    private String nombre_rutina;

    @NotEmpty(message = "Se requiere una descripcion de la rutina.")
    private String descripcion;

    private List<RutinaEjercicioSinRutinaDto> rutinaEjercicios = new ArrayList<>(); //no me importa rutina aqui --corregido

}
