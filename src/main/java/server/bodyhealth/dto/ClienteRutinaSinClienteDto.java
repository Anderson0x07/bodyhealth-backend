package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRutinaSinClienteDto {

    private int id_clienterutina;

    private RutinaDto rutina;

}
