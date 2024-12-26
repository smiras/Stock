package com.catsoftwarefactory.catstock.Service;

import com.catsoftwarefactory.catstock.Entity.Producto;
import com.catsoftwarefactory.catstock.Entity.SalidaProducto;
import com.catsoftwarefactory.catstock.Entity.User;
import com.catsoftwarefactory.catstock.Repository.ProductoRepository;
import com.catsoftwarefactory.catstock.Repository.SalidaProductoRepository;
import com.catsoftwarefactory.catstock.Request.ProductoRequest;
import com.catsoftwarefactory.catstock.Response.ProductoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UserService userService;

    public ResponseEntity<String> nuevo(ProductoRequest request) {
        Producto producto = Producto.builder()
                .codigo(request.getCodigo())
                .nombre(request.getNombre())
                .detalle(request.getDetalle())
                .cantidad(request.getCantidad())
                .costo(request.getCosto())
                .user(userService.obtenerUsuarioAutenticado())
                .build();

        productoRepository.save(producto);
        return ResponseEntity.ok("Producto guardado correctamente");
    }

    public List<Producto> listar() {
        return productoRepository.findAllByUser(userService.obtenerUsuarioAutenticado());
    }

    public Producto nuevo(Producto producto) {
        User user = userService.obtenerUsuarioAutenticado();
        producto.setUser(user);
        producto.setPrecio(userService.calcularPrecio(producto.getCosto(), user.getMargen()));
        return productoRepository.save(producto);
    }

    public Producto actualizar(Producto producto) {
        User user = userService.obtenerUsuarioAutenticado();
        Producto newProduct = buscarPorId(producto.getId());
        newProduct.setCodigo(producto.getCodigo());
        newProduct.setNombre(producto.getNombre());
        newProduct.setDetalle(producto.getDetalle());
        newProduct.setCantidad(producto.getCantidad());
        newProduct.setCosto(producto.getCosto());
        newProduct.setPrecio(userService.calcularPrecio(producto.getCosto(), user.getMargen()));
        return productoRepository.save(newProduct);
    }

    public ResponseEntity<String> delete(Integer id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return ResponseEntity.ok("Producto eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
    }

    public ResponseEntity<String> desactivar(Integer id) {
        Optional<Producto> productoOptinal = productoRepository.findById(id);
        if (productoOptinal.isPresent()) {
            Producto producto = productoOptinal.get();
            producto.setEstado(false);
            productoRepository.save(producto);
            return ResponseEntity.ok("Producto desactivado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
    }

    public ResponseEntity<String> activar(Integer id) {
        Optional<Producto> productoOptinal = productoRepository.findById(id);
        if (productoOptinal.isPresent()) {
            Producto producto = productoOptinal.get();
            producto.setEstado(true);
            productoRepository.save(producto);
            return ResponseEntity.ok("Producto activado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
    }

    public ProductoResponse buscarPorCodigo(String codigo) {
        Producto producto = productoRepository.findByCodigoAndUser(codigo, userService.obtenerUsuarioAutenticado())
                .orElseThrow(() -> new ProductoNoEncontradoException("Producto con código " + codigo + " no encontrado"));
        return ProductoResponse.builder()
                .id(producto.getId())
                .codigo(producto.getCodigo())
                .nombre(producto.getNombre())
                .detalle(producto.getDetalle())
                .cantidad(producto.getCantidad())
                .costo(producto.getCosto())
                .precio(producto.getPrecio())
                .estado(producto.isEstado())
                .nameProveedor(producto.getNameProveedor())
                .idProveedor(producto.getIdProveedor())
                .build();
    }

    public Producto buscarPorId(Long id) {
        return productoRepository.findByIdAndUser(id, userService.obtenerUsuarioAutenticado())
                .orElseThrow(() -> new ProductoNoEncontradoException("Producto con ID " + id + " no encontrado"));
    }

    public List<Producto> buscarPorIds(List<Long> ids){
        return productoRepository.findAllByIdInAndUser(ids, userService.obtenerUsuarioAutenticado());
    }

    public List<Producto> findByName(String name) {
        return productoRepository.findByNombreContainingAndUser(name, userService.obtenerUsuarioAutenticado());
    }

    public List<Producto> obtenerBajaExistencia() {
        User user = userService.obtenerUsuarioAutenticado();
        return productoRepository.findByUserAndCantidadLessThanAndEstado(userService.obtenerUsuarioAutenticado(), user.getMinimoStock(), true);
    }

    public List<Producto> listarActivos() {
        return productoRepository.findAllByUserAndEstado(userService.obtenerUsuarioAutenticado(), true);
    }

    public List<Producto> listarInactivos() {
        return productoRepository.findAllByUserAndEstado(userService.obtenerUsuarioAutenticado(), false);
    }

    public static class ProductoNoEncontradoException extends RuntimeException {
        public ProductoNoEncontradoException(String mensaje) {
            super(mensaje);
        }
    }
}

