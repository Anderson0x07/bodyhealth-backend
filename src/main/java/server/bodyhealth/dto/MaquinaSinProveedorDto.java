package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaquinaSinProveedorDto {
    private int id;
    @Positive
    private int  id_maquina;

    @NotEmpty(message = "Se requiere un nombre para la máquina")
    private  String nombre;

    @NotEmpty(message = "Se requiere un estado para la máquina")
    private String estado;

    @NotEmpty(message = "Se requiere una Observación para la máquina")
    private String observacion;
}
