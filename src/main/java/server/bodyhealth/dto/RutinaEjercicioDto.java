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
public class RutinaEjercicioDto {

    private int id_rutina_ejercicio;

    private RutinaDto rutina;

    private EjercicioDto ejercicio;

}
