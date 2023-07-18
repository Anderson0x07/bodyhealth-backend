package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoCompletoDto {
    private int id_producto;

    @NotEmpty(message = "Se requiere un nombre para el producto.")
    private String nombre;


    private int stock;

    @DecimalMin("100.0")
    private double precio;

    private  String foto;

    private boolean estado;

    private String tipo;

    private ProveedorDto proveedor;

    private List<PedidoDto> pedidos = new ArrayList<>();
}
