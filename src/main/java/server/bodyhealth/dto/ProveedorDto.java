package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorDto {

    private int id_proveedor;
    @NotEmpty(message = "Nombre de empresa es requerido")
    private String nombre_empresa;
    @NotEmpty(message = "dirección de empresa es requerido")
    private String direccion;
    @NotEmpty(message = "telefono de empresa es requerido")
    private String telefono;
}
