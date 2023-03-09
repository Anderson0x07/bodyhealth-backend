package server.bodyhealth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.entity.Maquina;
import server.bodyhealth.service.DetalleService;
import server.bodyhealth.service.MaquinaService;
import server.bodyhealth.service.ProveedorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/maquinas")
@Slf4j
public class MaquinaController {
    @Autowired
    private MaquinaService maquinaService;
    @Autowired
    private ProveedorService proveedorService;
    private String msj = "";

    @GetMapping("/all-maquinas")
    public ResponseEntity<List<Maquina>> listarMaquinas(){
        List<Maquina> maquinas = maquinaService.listarMaquinas();
        if (!maquinas.isEmpty()) {
            return ResponseEntity.ok(maquinas);
        } else {
            return ResponseEntity.noContent().build();
        }
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

    @GetMapping("/{id_maquina}")
    public ResponseEntity<Maquina> getMaquina(@PathVariable int id_maquina) {
        Maquina maquina = maquinaService.encontrarMaquina(id_maquina);
        if (maquina == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(maquina);
    }

    //Guarda edición de maquina en el dashboard del admin
    @PostMapping("/admin/dash-maquinas/expand/guardar")
    public String guardarEdicionMaquina(Maquina maquina) {

        log.info("Maquina: "+maquina.toString());

        List<Maquina> maquinas = maquinaService.listarMaquinas();

        if(maquinas.size()>0) {
            for (Maquina maq : maquinas) {
                if (maquina.getId_maquina() == maq.getId_maquina()) {
                    msj = "Error al editar la maquina, ya existe";
                    return "redirect:/admin/dash-maquinas/expand/editar/" + maquina.getId();
                }

            }
        }

        maquinaService.guardar(maquina);

        msj = "Maquina editada con exito";
        return "redirect:/admin/dash-maquinas/expand/"+maquina.getId();
    }


    @GetMapping("/admin/dash-maquinas/expand/editar/{id_maquina}")
    public String editar(Maquina maquina, Model model){

        maquina = maquinaService.encontrarMaquina(maquina.getId_maquina());

        model.addAttribute("maquina",maquina);
        model.addAttribute("proveedores",proveedorService.listarProveedores());
        model.addAttribute("msj",msj);
        msj="";

        return "admin/maquinas/maquina-editar";
    }

    @GetMapping("/admin/dash-maquinas/eliminar")
    public String eliminarMaquina(Maquina maquina){
        msj = "Maquina eliminada con exito";
        maquinaService.eliminar(maquina);
        return "redirect:/admin/dash-maquinas";
    }
}
