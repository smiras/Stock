package com.catsoftwarefactory.catstock.RestController;

import com.catsoftwarefactory.catstock.Entity.Cliente;
import com.catsoftwarefactory.catstock.Entity.Proveedor;
import com.catsoftwarefactory.catstock.Request.ClienteRequest;
import com.catsoftwarefactory.catstock.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"${cors.allowed.origins}"})
@RestController
@RequestMapping("/cliente")
public class ClienteRestController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/new")
    public ResponseEntity<Cliente> nuevo(@RequestBody ClienteRequest request) {
        return clienteService.nuevo(request);
    }

    @GetMapping("/list")
    public List<Cliente> listar(){
        return clienteService.listar();
    }

    @PostMapping("/update")
    public ResponseEntity<String> actualizar(@RequestBody Cliente cliente){
        return clienteService.actualizar(cliente);
    }

    @GetMapping("/delete/{id}")
    public  ResponseEntity<String> delete(@PathVariable Long id){
        return clienteService.delete(id);
    }

    @GetMapping("/findByName")
    public List<Cliente> buscarProveedores(@RequestParam String nombre) {
        return clienteService.findByName(nombre);
    }

    @GetMapping("/findById/{id}")
    public Cliente buscarCliente(@PathVariable Long id) {
        return clienteService.findById(id);
    }
}
