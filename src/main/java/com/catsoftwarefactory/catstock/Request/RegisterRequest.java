package com.catsoftwarefactory.catstock.Request;

import com.catsoftwarefactory.catstock.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    String username;
    String password;
    String name;
    Role rol;
}
