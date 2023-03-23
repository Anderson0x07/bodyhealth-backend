package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDto {

    private int id_pedido;

    private ProductoDto producto;

    private CompraDto compra;

    @Positive(message = "La cantidad debe ser mayor a 0")
    private int cantidad;

    @Positive(message = "El total debe ser mayor a 0")
    private double total;
}
