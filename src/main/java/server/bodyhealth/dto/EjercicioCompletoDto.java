package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import server.bodyhealth.entity.RutinaEjercicio;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EjercicioCompletoDto {

    private int id_ejercicio;

    @NotEmpty(message = "Se requiere una descripcion del ejercicio")
    private String descripcion;

    @Positive(message = "El numero de series debe ser mayor o igual a 1")
    private int series;

    @Positive(message = "El numero de repeticiones debe ser mayor o igual a 1")
    private int repeticiones;

    private String url_video;

    private MusculoDto musculo;

    private List<RutinaEjercicio> rutinaEjercicios = new ArrayList<>();

}
