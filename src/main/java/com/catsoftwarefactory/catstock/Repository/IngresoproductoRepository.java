package com.catsoftwarefactory.catstock.Repository;

import com.catsoftwarefactory.catstock.Entity.IngresoProducto;
import com.catsoftwarefactory.catstock.Entity.SalidaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngresoproductoRepository extends JpaRepository<IngresoProducto, Long> {

    @Query("SELECT ip FROM IngresoProducto ip WHERE ip.ingreso.id = :ingresoId")
    List<IngresoProducto> findAllByIngresoId(@Param("ingresoId") Long ingresoId);

}
