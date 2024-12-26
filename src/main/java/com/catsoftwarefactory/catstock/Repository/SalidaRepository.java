package com.catsoftwarefactory.catstock.Repository;

import com.catsoftwarefactory.catstock.Entity.Producto;
import com.catsoftwarefactory.catstock.Entity.Salida;
import com.catsoftwarefactory.catstock.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalidaRepository extends JpaRepository<Salida,Integer> {

    List<Salida> findAllByUser(User user);
    Optional<Salida> findById(Long id);
    }
