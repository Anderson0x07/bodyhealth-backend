package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntrenadorClienteSinEntrenadorDto {
    private int id_asignacion;

    private ClienteDto cliente;

}
