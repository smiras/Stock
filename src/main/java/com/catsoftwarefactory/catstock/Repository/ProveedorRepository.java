package com.catsoftwarefactory.catstock.Repository;

import com.catsoftwarefactory.catstock.Entity.Proveedor;
import com.catsoftwarefactory.catstock.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,Integer> {

    List<Proveedor> findAllByUser(User user);

    List<Proveedor> findByNombreContainingAndUser(String name, User user);

    boolean existsById(Integer id);

    Optional<Proveedor> findByIdAndUser(Long id, User user);
}
