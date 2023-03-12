package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorDto {

    @NotEmpty(message = "Nombre de empresa es requerido")
    private String nombre_empresa;
    @NotEmpty(message = "direcció de empresa es requerido")
    private String direccion;
    @NotEmpty(message = "telefono de empresa es requerido")
    private String telefono;
}
