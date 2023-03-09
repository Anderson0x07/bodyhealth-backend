package server.bodyhealth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.entity.Maquina;
import server.bodyhealth.service.MaquinaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/maquina")
@Slf4j
public class MaquinaController {
    @Autowired
    private MaquinaService maquinaService;
    private String msj = "";

    @GetMapping("/all")
    public ResponseEntity<List<Maquina>> listarMaquinas(){
        List<Maquina> maquinas = maquinaService.listarMaquinas();
        if (!maquinas.isEmpty()) {
            return ResponseEntity.ok(maquinas);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id_maquina}")
    public ResponseEntity<Maquina> obtenerMaquina(@PathVariable int id_maquina) {
        Maquina maquina = maquinaService.encontrarMaquinaId(id_maquina);
        if (maquina == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(maquina);
    }

    //Guarda nueva maquina
    @PostMapping("/guardar")
    public ResponseEntity<Maquina> guardarNuevaMaquina(@RequestBody Maquina maquina){

        Maquina newMaquina = maquinaService.encontrarMaquina(maquina.getId_maquina());
        if (newMaquina == null) {
            maquinaService.guardar(maquina);
            return ResponseEntity.ok(maquina);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/editar/{id}")
    public ResponseEntity<Maquina> actualizarMaquina(@PathVariable int id, @RequestBody Maquina maquinaActualizada) {
        //Está buscando la máquina por la llave primaria
        Maquina maquinaExistente = maquinaService.encontrarMaquinaId(id);
        //SE PUEDE DEJAR TAMBIÉN DE ESTA FORMA YA QUE BUSCARIA POR EL ID QUE LA EMPRESA LE DE A LA MÁQUINA
        //Maquina maquinaExistente = maquinaService.encontrarMaquina(id);
        if (maquinaExistente != null) {
            // Actualizar el producto existente con los nuevos datos
            maquinaExistente.setEstado(maquinaActualizada.getEstado());
            maquinaExistente.setId_maquina(maquinaActualizada.getId_maquina());
            maquinaExistente.setNombre(maquinaActualizada.getNombre());
            maquinaExistente.setObservacion(maquinaActualizada.getObservacion());
            maquinaExistente.setId_proveedor(maquinaActualizada.getId_proveedor());

            maquinaService.guardar(maquinaExistente);
            // Devolver una respuesta exitosa con el producto actualizado
            return new ResponseEntity<>(maquinaExistente, HttpStatus.OK);
        } else {
            // Devolver una respuesta de error si el producto no existe
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarMaquina(@PathVariable int id) {
        //Está buscando la máquina por la llave primaria
        Maquina maquinaExistente = maquinaService.encontrarMaquinaId(id);
        //SE PUEDE DEJAR TAMBIÉN DE ESTA FORMA YA QUE BUSCARIA POR EL ID QUE LA EMPRESA LE DE A LA MÁQUINA
        //Maquina maquinaExistente = maquinaService.encontrarMaquina(id);
        if (maquinaExistente != null) {
            // Eliminar el producto existente
            maquinaService.eliminar(maquinaExistente);

            // Devolver una respuesta exitosa sin cuerpo
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // Devolver una respuesta de error si el producto no existe
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
