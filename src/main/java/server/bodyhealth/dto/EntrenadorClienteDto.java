package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntrenadorClienteDto {
    private int id_asignacion;

    private EntrenadorDto entrenador;

    private ClienteDto cliente;
}
