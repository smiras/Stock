package com.catsoftwarefactory.catstock.Service;

import com.catsoftwarefactory.catstock.Entity.SalidaProducto;
import com.catsoftwarefactory.catstock.Repository.SalidaProductoRepository;
import com.catsoftwarefactory.catstock.Response.SalidaProductoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalidaProductoService {

    @Autowired
    private SalidaProductoRepository salidaProductoRepository;

    public SalidaProductoResponse obtenerProductosPorSalidaId(Long salidaId) {
        List<SalidaProducto> salidaProductos = salidaProductoRepository.findAllBySalidaId(salidaId);
        List<SalidaProductoResponse.ProductoCantidad> productosCantidad = salidaProductos.stream()
                .map(salidaProducto -> new SalidaProductoResponse.ProductoCantidad(salidaProducto.getProducto(), salidaProducto.getCantidad()))
                .collect(Collectors.toList());
        return new SalidaProductoResponse(productosCantidad);
    }
}
