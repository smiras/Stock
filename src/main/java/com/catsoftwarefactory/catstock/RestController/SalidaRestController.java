package com.catsoftwarefactory.catstock.RestController;

import com.catsoftwarefactory.catstock.Entity.Producto;
import com.catsoftwarefactory.catstock.Entity.Salida;
import com.catsoftwarefactory.catstock.Entity.SalidaProducto;
import com.catsoftwarefactory.catstock.Request.SalidaRequest;
import com.catsoftwarefactory.catstock.Response.SalidaProductoResponse;
import com.catsoftwarefactory.catstock.Service.SalidaProductoService;
import com.catsoftwarefactory.catstock.Service.SalidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = {"${cors.allowed.origins}"})
@RestController
@RequestMapping("/salida")
public class SalidaRestController {

    @Autowired
    private SalidaService salidaService;

    @Autowired
    private SalidaProductoService salidaProductoService;

    @PostMapping("/new")
    public ResponseEntity<String> nuevo(@RequestBody SalidaRequest request) {
        return salidaService.nuevo(request);
    }

    @GetMapping("/list")
    public List<Salida> listar(){
        return salidaService.listar();
    }

    @GetMapping("/delete/{id}")
    public  ResponseEntity<String> delete(@PathVariable Integer id){
        return salidaService.delete(id);
    }

    @GetMapping("/findById/{id}")
    public Salida buscarPorId(@PathVariable Long id){
        return salidaService.buscarPorId(id);
    }

    @GetMapping("/productosPorSalida/{id}")
    public ResponseEntity<SalidaProductoResponse> obtenerProductosPorSalida(@PathVariable Long id) {
        SalidaProductoResponse response = salidaProductoService.obtenerProductosPorSalidaId(id);
        return ResponseEntity.ok(response);
    }
}
