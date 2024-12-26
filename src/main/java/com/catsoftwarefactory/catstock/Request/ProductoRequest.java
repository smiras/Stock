package com.catsoftwarefactory.catstock.Request;

import lombok.Data;

@Data
public class ProductoRequest {

    private Long id;
    private String codigo;
    private String nombre;
    private String detalle;
    private int cantidad;
    private float costo;
}
