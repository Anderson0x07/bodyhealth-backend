package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.dto.PedidoDto;
import server.bodyhealth.dto.ReporteDto;
import server.bodyhealth.service.PedidoService;
import server.bodyhealth.service.ReporteService;

import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ReporteService reporteService;

    private Map<String,Object> response = new HashMap<>();

    @GetMapping("/all")
    public ResponseEntity<?> listarPedidos(){
        response.clear();
        response.put("pedidos",pedidoService.listarPedidos());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPedidoByID(@PathVariable int id) {
        response.clear();
        response.put("pedido", pedidoService.encontrarPedido(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarPedido(@Valid @RequestBody PedidoDto pedidoDto){
        response.clear();

        pedidoService.guardar(pedidoDto);
        response.put("message", "Pedido guardado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarPedido(@PathVariable int id, @RequestBody PedidoDto pedidoDto) {
        response.clear();
        PedidoDto pedido = pedidoService.editarPedido(id,pedidoDto);
        response.put("message", "Pedido actualizado satisfactoriamente");
        response.put("pedido", pedido);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPedido(@PathVariable int id) {
        response.clear();
        pedidoService.eliminar(id);
        response.put("message", "Pedido eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    //GENERAR PDF
    @GetMapping( value = "/pdf/{id_compra}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generarPDF(@PathVariable int id_compra) throws FileNotFoundException, JRException {

        List<PedidoDto> pedidosDto = pedidoService.encontrarPedidoPorCompra(id_compra);

        Object[] arr = reporteService.llenarReporte(pedidosDto);

        HashMap<String, Object> map = (HashMap<String, Object>) arr[0];
        List<ReporteDto> reporteProductos = (List<ReporteDto>) arr[1];

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(reporteProductos);

        JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/FacturaBodyhealth.jrxml"));

        JasperPrint reporte = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);

        byte[] data = JasperExportManager.exportReportToPdf(reporte);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=factura-"+pedidosDto.get(0).getCompra().getCliente().getDocumento()+".pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);

    }

}
