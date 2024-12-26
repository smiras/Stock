package com.catsoftwarefactory.catstock.Request;

import lombok.Data;
import java.util.List;

@Data
public class IngresoRequest {

    private String fecha;
    private String detalle;
    private String proveedorName;
    private Long proveedorId;
    private float total;
    private String estado;
    private List<IngresoProductoRequest> productos;
}
