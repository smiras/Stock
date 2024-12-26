package com.catsoftwarefactory.catstock.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponse {

    private Long id;
    private String codigo;
    private String nombre;
    private String detalle;
    private float cantidad;
    private float costo;
    private float precio;
    private boolean estado;

    private String nameProveedor;
    private Long idProveedor;

}
