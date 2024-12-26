package com.catsoftwarefactory.catstock.Service;

import com.catsoftwarefactory.catstock.Entity.User;
import com.catsoftwarefactory.catstock.Enum.Role;
import com.catsoftwarefactory.catstock.Repository.UserRepository;
import com.catsoftwarefactory.catstock.Request.LoginRequest;
import com.catsoftwarefactory.catstock.Request.RegisterRequest;
import com.catsoftwarefactory.catstock.Response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        User usuario = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        String mensaje = switch (usuario.getRole()) {
            case SUPERADMIN -> "sites/admin/home.html";
            case ADMIN, USER -> "sites/home.html";
            default -> "user";
        };
        return AuthResponse.builder()
                .token(token)
                .rol(usuario.getRole())
                .active(usuario.getActive())
                .mensaje(mensaje)
                .userId(usuario.getId())
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode( request.getPassword()))
                .name(request.getName())
                .role(request.getRol())
                .build();
        userRepository.save(user);

       return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    public boolean checkSuper(Long userId){
        User user = userRepository.findById(userId);
        return user.getRole().equals(Role.SUPERADMIN);
    }

}
