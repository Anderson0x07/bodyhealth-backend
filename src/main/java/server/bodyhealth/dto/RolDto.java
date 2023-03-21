package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolDto {

    private int id_rol;

    @NotEmpty(message = "Se requiere un nombre del rol")
    private String nombre;
}
