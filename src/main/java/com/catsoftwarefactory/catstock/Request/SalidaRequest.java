package com.catsoftwarefactory.catstock.Request;

import com.catsoftwarefactory.catstock.Entity.SalidaProducto;
import lombok.Data;
import java.util.List;

@Data
public class SalidaRequest {

    private String fecha;
    private String detalle;
    private float total;
    private String clienteName;
    private Long idCliente;
    private List<SalidaProductoRequest> salidaProductos;
}
