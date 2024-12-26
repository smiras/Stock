package com.catsoftwarefactory.catstock.RestController;

import com.catsoftwarefactory.catstock.Entity.Ingreso;
import com.catsoftwarefactory.catstock.Entity.Producto;
import com.catsoftwarefactory.catstock.Entity.Salida;
import com.catsoftwarefactory.catstock.Service.InformeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/informes")
@CrossOrigin(origins = {"${cors.allowed.origins}"})
public class InformeRestController {



}
