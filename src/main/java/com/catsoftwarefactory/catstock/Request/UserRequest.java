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
public class UserRequest {

    private Long id;
    private String username;
    private String password;
    private String name;
    private Role role;
    private String empresa;

}
