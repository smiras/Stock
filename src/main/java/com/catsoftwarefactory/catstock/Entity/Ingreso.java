package com.catsoftwarefactory.catstock.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Ingreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fecha;
    private String detalle;
    private Float total;
    private String estado;

    private String nameProveedor;
    private Long idProveedor;

    @OneToMany(mappedBy = "ingreso", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<IngresoProducto> ingresoProductos;

    @ManyToOne
    private User user;
}
