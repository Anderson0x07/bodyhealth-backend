package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoImagenDto {

    private int id_producto;

    @NotEmpty(message = "Se requiere un nombre para el producto.")
    private String nombre;


    private int stock;

    @DecimalMin("100.0")
    private double precio;

    private MultipartFile foto;

    private boolean estado;

    private ProveedorDto proveedor;
}
