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

    @NotEmpty(message = "Se requiere una descripción del musculo")
    private String descripcion;
}
