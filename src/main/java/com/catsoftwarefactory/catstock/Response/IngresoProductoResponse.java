package com.catsoftwarefactory.catstock.Response;

import com.catsoftwarefactory.catstock.Entity.Producto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngresoProductoResponse {

    private Long id;
    private String nameProveedor;
    private String fecha;
    private List<IngresoProductoResponse.ProductoCantidad> productos;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductoCantidad {
        private Producto producto;
        private int cantidad;
    }
}
