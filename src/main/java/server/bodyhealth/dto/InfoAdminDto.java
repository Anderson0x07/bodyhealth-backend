package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoAdminDto {
    private int maquinas;
    private int rutinas;
    private int musculos;
    private int ejercicios;
    private int clientes;
    private int entrenadores;
    private int productos;
    private int compras;
    private int proveedores;
    private int pedidos;

    private List<FacturasPlanesDto> facturasPorMesPlanes;
    private List<FacturasProductosDto> facturasPorMesProductos;

}
