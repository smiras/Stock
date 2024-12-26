package com.catsoftwarefactory.catstock.Request;

import lombok.Data;

@Data
public class IngresoProductoRequest {

    private Long id;
    private String codigo;
    private String nombre;
    private String detalle;
    private float costo;
    private int cantidad;
}
