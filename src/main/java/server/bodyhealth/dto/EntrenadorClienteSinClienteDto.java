package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntrenadorClienteSinClienteDto {
    private int id_asignacion;

    private EntrenadorDto entrenador;

}
