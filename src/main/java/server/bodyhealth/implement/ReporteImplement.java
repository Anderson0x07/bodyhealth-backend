package server.bodyhealth.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.bodyhealth.dto.*;
import server.bodyhealth.service.InfoBasicaService;
import server.bodyhealth.service.ReporteService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ReporteImplement implements ReporteService {

    @Autowired
    private InfoBasicaService infoBasicaService;

    @Override
    public Object[] llenarReporteCompras(List<PedidoDto> pedidosDto) {

        ClienteDto cliente = pedidosDto.get(0).getCompra().getCliente();

        HashMap<String, Object> map = llenarInfoBasica(cliente);

        map.put("factura", pedidosDto.get(0).getCompra().getId_compra());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
        String fechaCompra = dateFormat.format(pedidosDto.get(0).getCompra().getFecha_compra());

        map.put("fechaCompra", fechaCompra);

        List<ReporteDto> reporteProductos = new ArrayList<>();

        double subtotal = 0;
        for (int i = 0; i < pedidosDto.size(); i++) {
            int cantidad = pedidosDto.get(i).getCantidad();
            String nombre = pedidosDto.get(i).getProducto().getNombre();
            double precio = pedidosDto.get(i).getProducto().getPrecio();
            double importe = pedidosDto.get(i).getTotal();
            reporteProductos.add(new ReporteDto(cantidad, nombre, precio, importe));
            subtotal += importe;
        }
        double iva = subtotal*0.19;
        map.put("subtotal", subtotal);
        map.put("iva", iva);
        map.put("total", subtotal+iva);

        Object[] arr = new Object[2];

        arr[0] = map;
        arr[1] = reporteProductos;

        return arr;
    }

    @Override
    public Object[] llenarReportePlan(ClienteDetalleDto clienteDetalleDto) {

        ClienteDto cliente = clienteDetalleDto.getCliente();

        HashMap<String, Object> map = llenarInfoBasica(cliente);

        map.put("factura", clienteDetalleDto.getId_factura());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
        String fechaCompra = dateFormat.format(clienteDetalleDto.getFecha_inicio());

        map.put("fechaCompra", fechaCompra);

        int meses = clienteDetalleDto.getPlan().getMeses();

        map.put("total", clienteDetalleDto.getPlan().getPrecio());

        List<ReportePlanDto> plan = new ArrayList<>();
        plan.add(new ReportePlanDto(clienteDetalleDto.getPlan().getPlan(),
                                    (meses > 1 ? meses+" meses" : meses+" mes"),
                                    dateFormat.format(clienteDetalleDto.getFecha_inicio()),
                                    dateFormat.format(clienteDetalleDto.getFecha_fin())));

        Object[] arr = new Object[2];

        arr[0] = map;
        arr[1] = plan;

        return arr;
    }

    private HashMap<String, Object> llenarInfoBasica(ClienteDto cliente){

        HashMap<String, Object> map = new HashMap<>();

        InfoBasicaDto infoBasicaDto = infoBasicaService.encontrarInfoBasica(1);

        map.put("nombreEmpresa", infoBasicaDto.getNombre_empresa());
        map.put("rut", infoBasicaDto.getRut());
        map.put("direccion", infoBasicaDto.getDireccion());
        map.put("emailEmpresa", infoBasicaDto.getEmail());
        map.put("telefonoEmpresa", infoBasicaDto.getTelefono());
        map.put("nombre", cliente.getNombre()+" "+cliente.getApellido());
        map.put("identificacion", cliente.getTipo_documento()+" - "+cliente.getDocumento());
        map.put("telefono", cliente.getTelefono());
        map.put("email", cliente.getEmail());

        return map;
    }

}
