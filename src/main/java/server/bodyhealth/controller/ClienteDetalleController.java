package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.dto.ClienteDetalleDto;
import server.bodyhealth.dto.ReportePlanDto;
import server.bodyhealth.service.ClienteDetalleService;
import server.bodyhealth.service.ReporteService;

import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientedetalle")
@CrossOrigin
@Slf4j
public class ClienteDetalleController {
    @Autowired
    private ClienteDetalleService clienteDetalleService;

    @Autowired
    private ReporteService reporteService;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> listarClienteDetalles(){
        response.clear();
        response.put("clientedetalles",clienteDetalleService.listarClientesDetalles());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CLIENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerClienteDetalleByID(@PathVariable int id) {
        response.clear();
        response.put("clientedetalle", clienteDetalleService.encontrarClienteDetalle(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarClienteDetalle(@Valid @RequestBody ClienteDetalleDto clienteDetalleDto){
        response.clear();
        log.info(clienteDetalleDto.toString());
        int id_factura = clienteDetalleService.guardar(clienteDetalleDto);
        response.put("message", "Cliente detalle guardado satisfactoriamente");
        response.put("id_factura", id_factura);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarClienteDetalle(@PathVariable int id, @RequestBody ClienteDetalleDto clienteDetalleDto) {
        response.clear();
        ClienteDetalleDto clienteDetalle =  clienteDetalleService.editarClienteDetalle(id,clienteDetalleDto);
        response.put("message", "Cliente detalle actualizado satisfactoriamente");
        response.put("clientedetalle", clienteDetalle);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarClienteDetalle(@PathVariable int id) {
        response.clear();
        clienteDetalleService.eliminar(id);
        response.put("message", "Cliente detalle eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    //GENERAR PDF DE PEDIDOS
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CLIENTE')")
    @GetMapping( value = "/pdf/{id_factura}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generarPDF(@PathVariable int id_factura) throws FileNotFoundException, JRException, SQLException {

        ClienteDetalleDto clienteDetalleDto = clienteDetalleService.encontrarClienteDetalle(id_factura);

        Object[] arr = reporteService.llenarReportePlan(clienteDetalleDto);

        HashMap<String, Object> map = (HashMap<String, Object>) arr[0];

        List<ReportePlanDto> reportePlan = (List<ReportePlanDto>) arr[1];

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(reportePlan);

        JasperReport compileReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/FacturaPlanBodyhealth.jrxml"));

        JasperPrint reporte = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);

        byte[] data = JasperExportManager.exportReportToPdf(reporte);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=factura-plan-"+clienteDetalleDto.getCliente().getDocumento()+".pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);

    }
}
