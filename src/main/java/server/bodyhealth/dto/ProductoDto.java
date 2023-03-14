package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import server.bodyhealth.entity.Proveedor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDto {

    private int id_producto;

    @NotEmpty(message = "Se requiere un nombre para el producto.")
    private String nombre;


    private int stock;

    @DecimalMin("100.0")
    private double precio;

    private  String foto;

    private boolean estado;

    private ProveedorDto proveedor;
}
