package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import server.bodyhealth.entity.ClienteRutinaEjercicio;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRutinaCompletoDto {

    private int id_clienterutina;

    private ClienteDto cliente;

    private RutinaDto rutina;

    private List<ClienteRutinaEjercicioDto> clienteRutinaEjercicios = new ArrayList<>();
}
