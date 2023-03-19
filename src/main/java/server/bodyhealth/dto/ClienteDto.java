package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    private String foto;

    @NotEmpty(message = "Requiere una jornada.")
    private String jornada;

    @NotEmpty(message = "Requiere un comentario.")
    private String comentario;

    private boolean estado;
}
