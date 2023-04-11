package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RutinaEjercicioSinRutinaDto {

    private int id_rutina_ejercicio;

    private EjercicioDto ejercicio;

}
