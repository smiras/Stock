package com.catsoftwarefactory.catstock.Repository;

import com.catsoftwarefactory.catstock.Entity.Ingreso;
import com.catsoftwarefactory.catstock.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngresoRepository extends JpaRepository<Ingreso,Integer> {

    List<Ingreso> findAllByUser(User user);
    List<Ingreso> findByEstadoAndUser(String estado, User user);
    Optional<Ingreso> findById(Long id);
}
