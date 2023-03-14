package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RutinaDto {

    private int id_rutina;

    @NotEmpty(message = "Se requiere nombre para la rutina.")
    private String nombre_rutina;

    @NotEmpty(message = "Se requiere una descripcion de la rutina.")
    private String descripcion;
}
