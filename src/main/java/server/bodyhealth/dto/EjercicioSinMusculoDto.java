package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EjercicioSinMusculoDto {

    private int id_ejercicio;

    @NotEmpty(message = "Se requiere una descripcion del ejercicio")
    private String descripcion;

    @Positive(message = "El numero de series debe ser mayor o igual a 1")
    private int series;

    @Positive(message = "El numero de repeticiones debe ser mayor o igual a 1")
    private int repeticiones;

    private String url_video;

}
