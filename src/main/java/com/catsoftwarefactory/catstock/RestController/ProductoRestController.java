package com.catsoftwarefactory.catstock.RestController;

import com.catsoftwarefactory.catstock.Entity.Producto;
import com.catsoftwarefactory.catstock.Request.ProductoRequest;
import com.catsoftwarefactory.catstock.Response.ProductoResponse;
import com.catsoftwarefactory.catstock.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"${cors.allowed.origins}"})
@RestController
@RequestMapping("/producto")
public class ProductoRestController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/new")
    public ResponseEntity<String> nuevo(@RequestBody ProductoRequest request) {
        return productoService.nuevo(request);
    }

    @GetMapping("/list")
    public List<Producto> listar(){
        return productoService.listar();
    }

    @GetMapping("/listActive")
    public List<Producto> listarActivos(){
        return productoService.listarActivos();
    }

    @GetMapping("/listInactive")
    public List<Producto> listarInactivos(){
        return productoService.listarInactivos();
    }

    @PostMapping("/update")
    public Producto actualizar(@RequestBody Producto producto){
        return productoService.actualizar(producto);
    }

    @GetMapping("/delete/{id}")
    public  ResponseEntity<String> delete(@PathVariable Integer id){
        return productoService.delete(id);
    }

    @PostMapping("/findByCode")
    public ResponseEntity<ProductoResponse> buscarPorCodigo(@RequestBody String codigo){
        return ResponseEntity.ok(productoService.buscarPorCodigo(codigo));
    }

    @GetMapping("/findByName")
    public List<Producto> buscarPorNombre(@RequestParam String nombre) {
        return productoService.findByName(nombre);
    }

    @GetMapping("/findById")
    public Producto buscarPorId(@RequestParam Long id) {
        return productoService.buscarPorId(id);
    }

    @PostMapping("/findAllById")
    public List<Producto> buscarTodosPorId(@RequestBody List<Long> ids) {
        return productoService.buscarPorIds(ids);
    }

    @GetMapping("/findBajaExistencia")
    public List<Producto> bajaExistencia() {
        return productoService.obtenerBajaExistencia();
    }

    @GetMapping("/activate/{id}")
    public  ResponseEntity<String> activate(@PathVariable Integer id){
        return productoService.activar(id);
    }

    @GetMapping("/desactivate/{id}")
    public  ResponseEntity<String> desactivate(@PathVariable Integer id){
        return productoService.desactivar(id);
    }
}
