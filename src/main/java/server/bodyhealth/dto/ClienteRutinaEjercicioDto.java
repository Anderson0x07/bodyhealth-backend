package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRutinaEjercicioDto {

    private int id_cliente_rutina_ejercicio;

    private ClienteRutinaDto clienteRutina;

    private RutinaEjercicioDto rutinaEjercicio;

}
