package com.catsoftwarefactory.catstock.RestController;

import com.catsoftwarefactory.catstock.Entity.Proveedor;
import com.catsoftwarefactory.catstock.Request.ProveedorRequest;
import com.catsoftwarefactory.catstock.Service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"${cors.allowed.origins}"})
@RestController
@RequestMapping("/proveedor")
public class ProveedorRestController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping("/new")
    public ResponseEntity<String> nuevo(@RequestBody ProveedorRequest request) {
        return proveedorService.nuevo(request);
    }

    @GetMapping("/list")
    public List<Proveedor> listar(){
        return proveedorService.listar();
    }

    @PostMapping("/update")
    public ResponseEntity<Proveedor> actualizar(@RequestBody Proveedor proveedor){
        return proveedorService.actualizar(proveedor);
    }

    @GetMapping("/delete/{id}")
    public  ResponseEntity<String> delete(@PathVariable Integer id){
        return proveedorService.delete(id);
    }

    @GetMapping("/findByName")
    public List<Proveedor> buscarProveedores(@RequestParam String nombre) {
        return proveedorService.findByName(nombre);
    }
}
