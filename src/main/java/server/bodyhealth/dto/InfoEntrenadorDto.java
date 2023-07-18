package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoEntrenadorDto {
    private String nombre;
    private String jornada;

    private int id;
    private int clientes;
    private int rutinas;
    private int musculos;
    private int ejercicios;
}
