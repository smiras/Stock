package com.catsoftwarefactory.catstock.Service;

import com.catsoftwarefactory.catstock.Entity.Proveedor;
import com.catsoftwarefactory.catstock.Repository.ProveedorRepository;
import com.catsoftwarefactory.catstock.Request.ProveedorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private UserService userService;

    public ResponseEntity<String> nuevo(ProveedorRequest request) {
        Proveedor proveedor = Proveedor.builder()
                .nombre(request.getNombre())
                .domicilio(request.getDomicilio())
                .telefono(request.getTelefono())
                .mail(request.getMail())
                .detalle(request.getDetalle())
                .user(userService.obtenerUsuarioAutenticado())
                .build();
        proveedorRepository.save(proveedor);
        return ResponseEntity.ok("Proveedor guardado correctamente");
    }

    public List<Proveedor> listar() {
        return proveedorRepository.findAllByUser(userService.obtenerUsuarioAutenticado());
    }

    public ResponseEntity<Proveedor> actualizar(Proveedor proveedor) {
        proveedor.setUser(userService.obtenerUsuarioAutenticado());
        return ResponseEntity.ok(proveedorRepository.save(proveedor));
    }

    public ResponseEntity<String> delete(Integer id) {
        if (proveedorRepository.existsById(id)) {
            proveedorRepository.deleteById(id);
            return ResponseEntity.ok("Proveedor eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proveedor no encontrado");
        }
    }

    public Proveedor buscar(Long id) {
        return proveedorRepository.findByIdAndUser(id, userService.obtenerUsuarioAutenticado())
                .orElseThrow(() -> new ProveedorNoEncontradoException("Proveedor con ID " + id + " no encontrado"));
    }

    public List<Proveedor> findByName(String name) {
        return proveedorRepository.findByNombreContainingAndUser(name, userService.obtenerUsuarioAutenticado());
    }

    public static class ProveedorNoEncontradoException extends RuntimeException {
        public ProveedorNoEncontradoException(String mensaje) {
            super(mensaje);
        }
    }
}

