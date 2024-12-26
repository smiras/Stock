package com.catsoftwarefactory.catstock.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    private User user;

}
