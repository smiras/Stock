package com.catsoftwarefactory.catstock.Repository;

import com.catsoftwarefactory.catstock.Entity.Cliente;
import com.catsoftwarefactory.catstock.Entity.Proveedor;
import com.catsoftwarefactory.catstock.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findAllByUser(User user);

    List<Cliente> findByNombreContainingAndUser(String name, User user);
}
