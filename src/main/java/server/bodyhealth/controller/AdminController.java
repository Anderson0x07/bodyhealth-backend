package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import server.bodyhealth.dto.AdminDto;
import server.bodyhealth.entity.Compra;
import server.bodyhealth.entity.Usuario;
import server.bodyhealth.service.AdminService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin
@Slf4j
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Map<String,Object> response = new HashMap<>();

    @GetMapping("/all")
    public ResponseEntity<?> listarAdministradores(){
        response.clear();
        response.put("Administradores",adminService.listarAdmins());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/mi-perfil/{id_admin}")
    public ResponseEntity<?> perfilAdmin(@PathVariable int id_admin){
        response.clear();
        response.put("admin", adminService.encontrarAdmin(id_admin));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarAdministrador(AdminDto adminDto, @RequestPart(name = "file", required = false) MultipartFile file){
        response.clear();
        AdminDto adminDto1 = adminService.loadImage(file,adminDto);
        adminDto.setPassword(bCryptPasswordEncoder.encode(adminDto1.getPassword()));
        adminService.guardar(adminDto1);
        response.put("message", "Administrador guardado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarAdministrador(@PathVariable int id,AdminDto adminDto,@RequestPart(name = "file", required = false) MultipartFile file) {
        response.clear();
        AdminDto adminDto1 = adminService.loadImage(file,adminDto);
        adminService.editarAdmin(id,adminDto1);
        response.put("message", "Administrador actualizado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarAdministrador(@PathVariable int id) {
        response.clear();
        adminService.eliminar(id);
        response.put("message", "Administrador eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerAdminByID(@PathVariable int id) {
        response.clear();
        response.put("admin", adminService.encontrarAdmin(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
