package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRutinaDto {

    private int id_clienterutina;

    private ClienteDto cliente;

    private RutinaDto rutina;

}
