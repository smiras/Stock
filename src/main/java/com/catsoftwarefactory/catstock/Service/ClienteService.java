package com.catsoftwarefactory.catstock.Service;

import com.catsoftwarefactory.catstock.Entity.Cliente;
import com.catsoftwarefactory.catstock.Repository.ClienteRepository;
import com.catsoftwarefactory.catstock.Request.ClienteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UserService userService;

    public ResponseEntity<Cliente> nuevo(ClienteRequest request) {
        Cliente cliente = Cliente.builder()
                .nombre(request.getNombre())
                .domicilio(request.getDomicilio())
                .telefono(request.getTelefono())
                .mail(request.getMail())
                .detalle(request.getDetalle())
                .user(userService.obtenerUsuarioAutenticado())
                .build();
        return ResponseEntity.ok(clienteRepository.save(cliente));
    }

    public List<Cliente> listar() {
        return clienteRepository.findAllByUser(userService.obtenerUsuarioAutenticado());
    }

    public ResponseEntity<String> actualizar(Cliente cliente) {
        cliente.setUser(userService.obtenerUsuarioAutenticado());
        clienteRepository.save(cliente);
        return ResponseEntity.ok("Cliente guardado correctamente");
    }

    public ResponseEntity<String> delete(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return ResponseEntity.ok("Cliente eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }
    }

    public Cliente buscar(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + id));
    }

    public List<Cliente> findByName(String name) {
        return clienteRepository.findByNombreContainingAndUser(name, userService.obtenerUsuarioAutenticado());
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow();
    }
}

