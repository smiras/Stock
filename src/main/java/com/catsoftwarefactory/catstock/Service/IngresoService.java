package com.catsoftwarefactory.catstock.Service;

import com.catsoftwarefactory.catstock.Entity.Ingreso;
import com.catsoftwarefactory.catstock.Entity.IngresoProducto;
import com.catsoftwarefactory.catstock.Entity.Producto;
import com.catsoftwarefactory.catstock.Entity.User;
import com.catsoftwarefactory.catstock.Repository.IngresoRepository;
import com.catsoftwarefactory.catstock.Request.IngresoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class IngresoService {

    @Autowired
    private IngresoRepository ingresoRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductoService productoService;

    public List<Ingreso> listar() {
        return ingresoRepository.findAllByUser(userService.obtenerUsuarioAutenticado());
    }

    public List<Ingreso> listarPendientes() {
        return ingresoRepository.findByEstadoAndUser("Pendiente", userService.obtenerUsuarioAutenticado());
    }

    public List<Ingreso> listarArchivo() {
        return ingresoRepository.findByEstadoAndUser("Pagado", userService.obtenerUsuarioAutenticado());
    }

    public ResponseEntity<String> nuevo(IngresoRequest request) {
        User user = userService.obtenerUsuarioAutenticado();
        float margen = (user.getMargen() / 100) + 1;

        Ingreso ingreso = Ingreso.builder()
                .user(userService.obtenerUsuarioAutenticado())
                .fecha(request.getFecha())
                .detalle(request.getDetalle())
                .idProveedor(request.getProveedorId())
                .nameProveedor(request.getProveedorName())
                .total(request.getTotal())
                .estado(request.getEstado())
                .build();

        List<IngresoProducto> ingresoProductos = new ArrayList<>();
        request.getProductos().forEach(p -> {
            Producto producto;
            if (p.getId() == null) {
                producto = Producto.builder()
                        .nombre(p.getNombre())
                        .codigo(p.getCodigo())
                        .cantidad(p.getCantidad())
                        .costo(p.getCosto())
                        .precio(p.getCosto() * margen)
                        .detalle(p.getDetalle())
                        .idProveedor(request.getProveedorId())
                        .nameProveedor(request.getProveedorName())
                        .build();
                producto = productoService.nuevo(producto);
            } else {
                producto = productoService.buscarPorId(p.getId());
                if (producto == null) {
                    throw new RuntimeException("Producto no encontrado: " + p.getId());
                }
                producto.setCantidad(producto.getCantidad() + p.getCantidad());
                producto.setCosto(p.getCosto());
                producto.setPrecio(p.getCosto() * margen);
                producto.setIdProveedor(request.getProveedorId());
                producto.setNameProveedor(request.getProveedorName());
                producto.setEstado(true);
                productoService.actualizar(producto);
            }
            IngresoProducto ingresoProducto = IngresoProducto.builder()
                    .producto(producto)
                    .ingreso(ingreso)
                    .cantidad(p.getCantidad())
                    .build();

            ingresoProductos.add(ingresoProducto);
        });

        ingreso.setIngresoProductos(ingresoProductos);

        ingresoRepository.save(ingreso);
        return ResponseEntity.ok("Ingreso guardado correctamente");
    }

    public ResponseEntity<String> delete(Integer id) {
        if (ingresoRepository.existsById(id)) {
            ingresoRepository.deleteById(id);
            return ResponseEntity.ok("Producto eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ingreso no encontrado");
        }
    }

    public Ingreso buscarPorId(Long id) {
        return ingresoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingreso no encontrado: " + id));
    }

    public String pagarPedido(Long id) {
        Ingreso ingreso = ingresoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingreso no encontrado: " + id));
        ingreso.setEstado("Pagado");
        ingresoRepository.save(ingreso);
        return "Estado cambiado correctamente";
    }
}

