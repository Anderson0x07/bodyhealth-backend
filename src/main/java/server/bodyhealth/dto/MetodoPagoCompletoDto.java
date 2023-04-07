package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetodoPagoCompletoDto {

    private int id_metodopago;

    @NotEmpty(message = "Se requiere una descripción del método de pago")
    private String descripcion;

    private List<CompraDto> compras = new ArrayList<>();

    private List<ClienteDetalleDto> clienteDetalles = new ArrayList<>();
}
