package com.catsoftwarefactory.catstock.Service;

import com.catsoftwarefactory.catstock.Entity.Producto;
import com.catsoftwarefactory.catstock.Entity.User;
import com.catsoftwarefactory.catstock.Enum.Role;
import com.catsoftwarefactory.catstock.Repository.ProductoRepository;
import com.catsoftwarefactory.catstock.Repository.UserRepository;
import com.catsoftwarefactory.catstock.Request.AjustesRequest;
import com.catsoftwarefactory.catstock.Request.CheckPassRequest;
import com.catsoftwarefactory.catstock.Request.UserRequest;
import com.catsoftwarefactory.catstock.Response.AjustesResponse;
import com.catsoftwarefactory.catstock.Response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private ProductoRepository productoRepository;

    public User obtenerUsuarioAutenticado() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        } else {
            return null;
        }
    }

    public UserResponse saveUser(UserRequest request){
        UserResponse response = new UserResponse();
        if (Objects.isNull(request.getId())) {
            if (userRepository.existsByUsername(request.getUsername())) {
                response.setMensaje("Ya existe ese nombre de usuario");
                response.setCreado(false);
            } else {
                User user = User.builder()
                        .username(request.getUsername())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .name(request.getName())
                        .role(request.getRole())
                        .build();
                userRepository.save(user);
                response.setMensaje("Usuario creado correctamente");
                response.setCreado(true);
            }
        }else {
            User user = User.builder()
                    .id(request.getId())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .name(request.getName())
                    .role(request.getRole())
                    .build();
            userRepository.save(user);
            response.setMensaje("Usuario guardado correctamente");
            response.setCreado(true);
        }
        return response;
    }

    public List<User> listarUsuarios(){
        return userRepository.findAll();
    }

    public User findById(Integer id){
        return userRepository.findById(id).get();
    }

    public UserResponse eliminar(Integer id){
        userRepository.deleteById(id);
        return UserResponse.builder()
                .mensaje("Empresa eliminada correctamente")
                .build();
    }

    public Boolean checkUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public Boolean checkPass (CheckPassRequest request) {
        User user = userRepository.findById(request.getId());
        return passwordEncoder.matches(request.getPass(), user.getPassword());
    }

    public AjustesResponse getAjustes(){
        User user = this.obtenerUsuarioAutenticado();

        return AjustesResponse.builder()
                .name(user.getName())
                .username(user.getUsername())
                .password(user.getPassword())
                .empresa(user.getEmpresa())
                .domicilio(user.getDomicilio())
                .telefono(user.getTelefono())
                .mail(user.getMail())
                .margen(user.getMargen())
                .minimoStock(user.getMinimoStock())
                .build();
    }

    public String saveAjustes(AjustesRequest request){
        User user = this.obtenerUsuarioAutenticado();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setEmpresa(request.getEmpresa());
        user.setDomicilio(request.getDomicilio());
        user.setTelefono(request.getTelefono());
        user.setMail(request.getMail());
        user.setMargen(request.getMargen());
        user.setMinimoStock(request.getMinimoStock());
        if(request.getPassword() != null){
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        userRepository.save(user);

        List<Producto> productos = productoRepository.findAllByUser(user);
        productos.forEach(producto -> {
            producto.setPrecio(calcularPrecio(producto.getCosto(), request.getMargen()));
        });
        productoRepository.saveAll(productos);
        return "Parámetros guardados correctamente";
    }

    public float calcularPrecio(float costo, float margenGanancia) {        // Convertimos el margen de ganancia de porcentaje a decimal
        float margenDecimal = margenGanancia / 100F;

        // Calculamos el precio utilizando la fórmula

        return costo / (1 - margenDecimal);
    }

    public List<User> ListarByEstado(Boolean estado) {
        return userRepository.findAllByActive(estado);
    }

    public Boolean changeState(Long id) {
        User user = userRepository.findById(id);
        user.setActive(!user.getActive());
        userRepository.save(user);
        return user.getActive();
    }
}
