package com.catsoftwarefactory.catstock.Service;

import com.catsoftwarefactory.catstock.Entity.Ingreso;
import com.catsoftwarefactory.catstock.Entity.IngresoProducto;
import com.catsoftwarefactory.catstock.Repository.IngresoRepository;
import com.catsoftwarefactory.catstock.Repository.IngresoproductoRepository;
import com.catsoftwarefactory.catstock.Response.IngresoProductoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngresoProductoService {

    @Autowired
    private IngresoproductoRepository ingresoProductoRepository;

    @Autowired
    private IngresoRepository ingresoRepository;

    public IngresoProductoResponse obtenerProductosPorIngresoId(Long ingresoId) {
        Ingreso ingreso = ingresoRepository.findById(ingresoId).orElse(new Ingreso());
        List<IngresoProducto> ingresoProductos = ingresoProductoRepository.findAllByIngresoId(ingresoId);
        List<IngresoProductoResponse.ProductoCantidad> productosCantidad = ingresoProductos.stream()
                .map(ingresoProducto -> new IngresoProductoResponse.ProductoCantidad(ingresoProducto.getProducto(), ingresoProducto.getCantidad()))
                .collect(Collectors.toList());
        return IngresoProductoResponse.builder()
                .id(ingresoId)
                .fecha(ingreso.getFecha())
                .nameProveedor(ingreso.getNameProveedor())
                .productos(productosCantidad)
                .build();
    }
}
