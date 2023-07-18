package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusculoDto {

    private int id_musculo;

    @NotEmpty(message = "Se requiere el nombre del musculo")
    private String nombre;
    @NotEmpty(message = "Se requiere el grupo muscular al que pertenece")
    private String grupo_muscular;
    private String descripcion;
}
