package com.catsoftwarefactory.catstock.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClienteRequest {

    private String nombre;
    private String domicilio;
    private String telefono;
    private String mail;
    private String detalle;
}
