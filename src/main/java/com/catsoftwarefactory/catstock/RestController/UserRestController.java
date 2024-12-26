package com.catsoftwarefactory.catstock.RestController;

import com.catsoftwarefactory.catstock.Entity.User;
import com.catsoftwarefactory.catstock.Request.AjustesRequest;
import com.catsoftwarefactory.catstock.Request.CheckPassRequest;
import com.catsoftwarefactory.catstock.Request.UserRequest;
import com.catsoftwarefactory.catstock.Response.AjustesResponse;
import com.catsoftwarefactory.catstock.Response.UserResponse;
import com.catsoftwarefactory.catstock.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/getUser")
    public ResponseEntity<User> obtenrUsuario(){
        User user = userService.obtenerUsuarioAutenticado();
        user.setPassword("");
        return ResponseEntity.ok(user);
    }

    @PostMapping("/save")
    public ResponseEntity<UserResponse> guardarUsuario(@RequestBody UserRequest request){
        return ResponseEntity.ok(userService.saveUser(request));
    }


    @PostMapping("/checkPass")
    public Boolean checkPass(@RequestBody CheckPassRequest request) {
        return userService.checkPass(request);
    }

    @GetMapping("/getAjustes")
    public AjustesResponse getAjustes(){
        return userService.getAjustes();
    }

    @PostMapping("/saveAjustes")
    public ResponseEntity<String> guardarAjustes(@RequestBody AjustesRequest request){
        return ResponseEntity.ok(userService.saveAjustes(request));
    }

    @GetMapping("/listByEstado/{estado}")
    public ResponseEntity<List<User>> ListarByEstado(@PathVariable Boolean estado){
        return ResponseEntity.ok(userService.ListarByEstado(estado));
    }

    @GetMapping("/changeState/{id}")
    public ResponseEntity<Boolean> changeState(@PathVariable Long id){
        return ResponseEntity.ok(userService.changeState(id));
    }

}
