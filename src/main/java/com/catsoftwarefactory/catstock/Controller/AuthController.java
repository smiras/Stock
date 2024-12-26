package com.catsoftwarefactory.catstock.Controller;

import com.catsoftwarefactory.catstock.Enum.Role;
import com.catsoftwarefactory.catstock.Request.ChekUserRequest;
import com.catsoftwarefactory.catstock.Request.LoginRequest;
import com.catsoftwarefactory.catstock.Request.RegisterRequest;
import com.catsoftwarefactory.catstock.Response.AuthResponse;
import com.catsoftwarefactory.catstock.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("/create")
    public ResponseEntity<AuthResponse> create(){
        RegisterRequest request = new RegisterRequest("admin","admin","Administrador", Role.SUPERADMIN);
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/checkSuper")
    public ResponseEntity<Boolean> checkSuper(@RequestBody ChekUserRequest request){
        return ResponseEntity.ok(authService.checkSuper(request.getId()));
    }

}
