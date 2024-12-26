package com.catsoftwarefactory.catstock.Repository;

import com.catsoftwarefactory.catstock.Entity.SalidaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalidaProductoRepository extends JpaRepository<SalidaProducto, Long> {
    @Query("SELECT sp FROM SalidaProducto sp WHERE sp.salida.id = :salidaId")
    List<SalidaProducto> findAllBySalidaId(@Param("salidaId") Long salidaId);
}