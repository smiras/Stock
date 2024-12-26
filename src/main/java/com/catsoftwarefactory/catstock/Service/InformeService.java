package com.catsoftwarefactory.catstock.Service;

import com.catsoftwarefactory.catstock.Entity.Ingreso;
import com.catsoftwarefactory.catstock.Entity.Producto;
import com.catsoftwarefactory.catstock.Entity.Salida;
import com.catsoftwarefactory.catstock.Repository.IngresoRepository;
import com.catsoftwarefactory.catstock.Repository.ProductoRepository;
import com.catsoftwarefactory.catstock.Repository.SalidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InformeService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private SalidaRepository salidaRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private IngresoRepository ingresoRepository;

    public ResponseEntity<List<Producto>> informeStockActual() {
        List<Producto> productos = productoRepository.findAllByUser(userService.obtenerUsuarioAutenticado());
        return ResponseEntity.ok(productos);
    }

    public ResponseEntity<List<Ingreso>> informeMovimientosIngreso() {
        List<Ingreso> ingresos = ingresoRepository.findAllByUser(userService.obtenerUsuarioAutenticado());
        return ResponseEntity.ok(ingresos);
    }

    public ResponseEntity<List<Salida>> informeMovimientosSalida() {
        List<Salida> salidas = salidaRepository.findAllByUser(userService.obtenerUsuarioAutenticado());
        return ResponseEntity.ok(salidas);
    }



    public ResponseEntity<Double> informeValorInventario() {
        List<Producto> productos = productoRepository.findAllByUser(userService.obtenerUsuarioAutenticado());
        double valorTotal = productos.stream()
                .mapToDouble(producto -> producto.getCantidad() * producto.getCosto())
                .sum();
        return ResponseEntity.ok(valorTotal);
    }


}
