package pe.edu.upeu.lp2_gp2.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upeu.lp2_gp2.dto.ProductoDTO;
import pe.edu.upeu.lp2_gp2.services.IProductoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
    private final IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }
    //listar productos
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listar(){
        return ResponseEntity.ok(productoService.listarTodo());
    }



}
