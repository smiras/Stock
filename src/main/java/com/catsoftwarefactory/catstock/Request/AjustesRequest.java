package com.catsoftwarefactory.catstock.Request;

import com.catsoftwarefactory.catstock.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AjustesRequest {

    private String username;
    private String password;
    private String name;
    private Role role;
    private String empresa;
    private String telefono;
    private String domicilio;
    private String mail;
    private float margen;
    private int minimoStock;
}
