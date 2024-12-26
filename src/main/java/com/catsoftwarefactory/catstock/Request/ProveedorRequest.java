package com.catsoftwarefactory.catstock.Request;

import lombok.Data;

@Data
public class ProveedorRequest {

    private String nombre;
    private String domicilio;
    private String telefono;
    private String mail;
    private String detalle;
}
