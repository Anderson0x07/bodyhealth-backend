package server.bodyhealth.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;
import org.springframework.core.io.ClassPathResource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Component
public class JasperReportManager {

    private static final String REPORT_FOLDER = "reports";

    private static final String JASPER = ".jasper";

    public ByteArrayOutputStream export(String fileName, Map<String, Object> params,
                                        Connection con) throws JRException, IOException {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ClassPathResource resource = new ClassPathResource(REPORT_FOLDER + File.separator + fileName + JASPER);

        InputStream inputStream = resource.getInputStream();
        JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, params, con);


        JasperExportManager.exportReportToPdfStream(jasperPrint, stream); //PDF


        return stream;
    }

    /*
    public void exportPedido(HttpServletResponse response, PedidoDto pedido) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(35);
        Font fontBody = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontBody.setSize(15);

        document.add(new Paragraph("Factura",fontTitle));
        document.add(new Paragraph("Gym BodyHealth",fontBody));
        document.add(new Paragraph("Direccion: XXXX",fontBody));

        document.add( Chunk.NEWLINE );

        PdfPTable factura = new PdfPTable(2);

        PdfPCell celda1 = new PdfPCell();
        Paragraph fac = new Paragraph("Factura #"+pedido.getId_pedido());
        fac.setAlignment(Element.ALIGN_CENTER); fac.setLeading(12f);
        celda1.addElement(fac);
        celda1.setColspan(2);
        factura.addCell(celda1);

        factura.addCell("Fecha de Compra");
        factura.addCell(dateFormat(pedido.getCompra().getFecha_compra()));

        PdfPCell info = new PdfPCell();
        Paragraph infoC = new Paragraph("Información del Producto");
        info.addElement(infoC);
        info.setRowspan(3);
        factura.addCell(info);

        factura.addCell("Nombre: "+pedido.getProducto().getNombre());
        factura.addCell("Proveedor: "+pedido.getProducto().getProveedor().getNombre_empresa());
        factura.addCell("Precio Unitario: $"+pedido.getProducto().getPrecio()+"");

        PdfPCell deta = new PdfPCell();
        Paragraph infoD = new Paragraph("Información del Cliente");
        deta.addElement(infoD);
        deta.setRowspan(4);
        factura.addCell(deta);

        factura.addCell("Identificación: "+pedido.getCompra().getCliente().getTipo_documento()+" - "+pedido.getCompra().getCliente().getDocumento());
        factura.addCell("Nombre: "+pedido.getCompra().getCliente().getNombre()+" "+pedido.getCompra().getCliente().getApellido());
        factura.addCell("Télefono: "+pedido.getCompra().getCliente().getTelefono());
        factura.addCell("Email: "+pedido.getCompra().getCliente().getEmail());

        factura.addCell("Cantidad");
        factura.addCell(pedido.getCantidad()+" Unidades");

        factura.addCell("Total");
        factura.addCell("$"+pedido.getCantidad()*pedido.getProducto().getPrecio());

        factura.addCell("Método de Pago");
        factura.addCell("Efectivo");


        factura.setSpacingAfter(300);

        document.add(factura);

        document.add(new Paragraph("Vendido por BodyHealth",fontBody));


        document.close();
    }

    public String dateFormat(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        return dateFormat.format(date);
    } */
}
