package com.catsoftwarefactory.catstock.Request;

import lombok.Data;

@Data
public class SalidaProductoRequest {

    private Long productoId;
    private int cantidad;
}
