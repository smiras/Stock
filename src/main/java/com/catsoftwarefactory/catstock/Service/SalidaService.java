package com.catsoftwarefactory.catstock.Service;

import com.catsoftwarefactory.catstock.Entity.*;
import com.catsoftwarefactory.catstock.Repository.SalidaRepository;
import com.catsoftwarefactory.catstock.Request.SalidaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalidaService {

    @Autowired
    private SalidaRepository salidaRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ClienteService clienteService;

    public List<Salida> listar() {
        return salidaRepository.findAllByUser(userService.obtenerUsuarioAutenticado());
    }

    public ResponseEntity<String> nuevo(SalidaRequest request) {

        Salida salida = new Salida();
        salida.setUser(userService.obtenerUsuarioAutenticado());
        salida.setFecha(request.getFecha());
        salida.setDetalle(request.getDetalle());
        salida.setIdCliente(request.getIdCliente());
        salida.setNameCliente(request.getClienteName());
        salida.setTotal(request.getTotal());

        List<SalidaProducto> salidaProductos = request.getSalidaProductos().stream().map(p -> {
            Producto producto = productoService.buscarPorId(p.getProductoId());
            if (producto == null) {
                throw new RuntimeException("Producto no encontrado: " + p.getProductoId());
            } else {
                producto.setCantidad(producto.getCantidad() - p.getCantidad());
                productoService.actualizar(producto);
            }
            return new SalidaProducto(null, salida, producto, p.getCantidad());
        }).collect(Collectors.toList());

        salida.setSalidaProductos(salidaProductos);

        salidaRepository.save(salida);
        return ResponseEntity.ok("Salida guardada correctamente");
    }

    public ResponseEntity<String> delete(Integer id) {
        if (salidaRepository.existsById(id)) {
            salidaRepository.deleteById(id);
            return ResponseEntity.ok("Salida eliminada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Salida no encontrada");
        }
    }

    public Salida buscarPorId(Long id) {
        return salidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salida no encontrada: " + id));
    }
}

