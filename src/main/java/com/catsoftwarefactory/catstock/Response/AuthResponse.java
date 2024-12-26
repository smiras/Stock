package com.catsoftwarefactory.catstock.Response;

import com.catsoftwarefactory.catstock.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String token;
    private Boolean active;
    private Role rol;
    private String mensaje;
    private Long userId;
}
