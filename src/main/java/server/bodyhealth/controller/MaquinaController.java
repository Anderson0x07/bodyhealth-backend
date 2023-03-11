package server.bodyhealth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.entity.Maquina;
import server.bodyhealth.service.MaquinaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/maquina")
@CrossOrigin
@Slf4j
public class MaquinaController {
    @Autowired
    private MaquinaService maquinaService;

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

    @PostMapping("/guardar")
    public ResponseEntity<Maquina> guardarMaquina(@RequestBody Maquina maquina){
        Maquina maquinaExiste = maquinaService.encontrarMaquina(maquina.getId_maquina());
        if (maquinaExiste == null) {
            maquinaService.guardar(maquina);
            return ResponseEntity.ok(maquina);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/editar/{id_maquina}")
    public ResponseEntity<Maquina> editarMaquina(@PathVariable int id_maquina, @RequestBody Maquina maquinaActualizada) {
        //Está buscando la máquina por la llave primaria
        Maquina maquinaExistente = maquinaService.encontrarMaquinaId(id_maquina);
        //SE PUEDE DEJAR TAMBIÉN DE ESTA FORMA YA QUE BUSCARIA POR EL ID QUE LA EMPRESA LE DE A LA MÁQUINA
        //Maquina maquinaExistente = maquinaService.encontrarMaquina(id);
        if (maquinaExistente != null) {
            // Actualizar el producto existente con los nuevos datos
            maquinaExistente.setEstado(maquinaActualizada.getEstado());
            maquinaExistente.setId_maquina(maquinaActualizada.getId_maquina());
            maquinaExistente.setNombre(maquinaActualizada.getNombre());
            maquinaExistente.setObservacion(maquinaActualizada.getObservacion());
            maquinaExistente.setProveedor(maquinaActualizada.getProveedor());

            maquinaService.guardar(maquinaExistente);
            // Devolver una respuesta exitosa con el producto actualizado
            return ResponseEntity.ok(maquinaExistente);
        } else {
            // Devolver una respuesta de error si el producto no existe
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/eliminar/{id_maquina}")
    public ResponseEntity<Void> eliminarMaquina(@PathVariable int id_maquina) {
        //Está buscando la máquina por la llave primaria
        Maquina maquinaExistente = maquinaService.encontrarMaquinaId(id_maquina);
        //SE PUEDE DEJAR TAMBIÉN DE ESTA FORMA YA QUE BUSCARIA POR EL ID QUE LA EMPRESA LE DE A LA MÁQUINA
        //Maquina maquinaExistente = maquinaService.encontrarMaquina(id);
        if (maquinaExistente != null) {
            // Eliminar el producto existente
            maquinaService.eliminar(maquinaExistente);

            // Devolver una respuesta exitosa sin cuerpo
            return ResponseEntity.noContent().build();
        } else {
            // Devolver una respuesta de error si el producto no existe
            return ResponseEntity.notFound().build();
        }
    }
}
