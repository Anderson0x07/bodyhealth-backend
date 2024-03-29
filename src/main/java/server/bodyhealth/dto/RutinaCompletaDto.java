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
public class RutinaCompletaDto {
    private int id_rutina;

    @NotEmpty(message = "Se requiere nombre para la rutina.")
    private String nombre_rutina;

    @NotEmpty(message = "Se requiere el nivel de la rutina.")
    private String nivel;

    @NotEmpty(message = "Se requiere la duracion de la rutina.")
    private String duracion;

    @NotEmpty(message = "Se requiere una descripcion de la rutina.")
    private String descripcion;

    private List<RutinaEjercicioSinRutinaDto> rutinaEjercicios = new ArrayList<>(); //no me importa rutina aqui --corregido

    private List<ClienteRutinaDto> clienteRutinas = new ArrayList<>();
}
