package com.catsoftwarefactory.catstock.Repository;

import com.catsoftwarefactory.catstock.Entity.Producto;
import com.catsoftwarefactory.catstock.Entity.User;
import com.catsoftwarefactory.catstock.Request.ProductoRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Integer> {

    List<Producto> findAllByUserAndEstado(User user, boolean estado);
    Optional<Producto> findByCodigoAndUser(String codigo, User user);

    List<Producto> findAllByUser(User principal);

    Optional<Producto> findByIdAndUser(Long id, User user);

    List<Producto> findByNombreContainingAndUser(String name, User user);

    List<Producto> findByUserAndCantidadLessThanAndEstado(User user, float cantidad, boolean estado);

    List<Producto> findAllByIdInAndUser(List<Long> ids, User user);


}
