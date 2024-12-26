package com.catsoftwarefactory.catstock.RestController;

import com.catsoftwarefactory.catstock.Entity.Ingreso;
import com.catsoftwarefactory.catstock.Request.IngresoRequest;
import com.catsoftwarefactory.catstock.Response.IngresoProductoResponse;
import com.catsoftwarefactory.catstock.Response.SalidaProductoResponse;
import com.catsoftwarefactory.catstock.Service.IngresoProductoService;
import com.catsoftwarefactory.catstock.Service.IngresoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"${cors.allowed.origins}"})
@RestController
@RequestMapping("/ingreso")
public class IngresoRestController {

    @Autowired
    private IngresoService ingresoService;

    @Autowired
    private IngresoProductoService ingresoProductoService;

    @PostMapping("/new")
    public ResponseEntity<String> nuevo(@RequestBody IngresoRequest request) {
        return ingresoService.nuevo(request);
    }

    @GetMapping("/list")
    public List<Ingreso> listar(){
        return ingresoService.listar();
    }

    @GetMapping("/listPendientes")
    public List<Ingreso> listarPendientes(){
        return ingresoService.listarPendientes();
    }

    @GetMapping("/listArchivo")
    public List<Ingreso> listarArchivo(){
        return ingresoService.listarArchivo();
    }

    @GetMapping("/delete/{id}")
    public  ResponseEntity<String> delete(@PathVariable Integer id){
        return ingresoService.delete(id);
    }

    @GetMapping("/findById/{id}")
    public Ingreso buscarPorId(@PathVariable Long id){
        return ingresoService.buscarPorId(id);
    }

    @GetMapping("/changeState/{id}")
    public  ResponseEntity<String> changeState(@PathVariable Long id){
        return ResponseEntity.ok(ingresoService.pagarPedido(id));
    }

    @GetMapping("/productosPorIngreso/{id}")
    public ResponseEntity<IngresoProductoResponse> obtenerProductosPorIngreso(@PathVariable Long id) {
        IngresoProductoResponse response = ingresoProductoService.obtenerProductosPorIngresoId(id);
        return ResponseEntity.ok(response);
    }
}
