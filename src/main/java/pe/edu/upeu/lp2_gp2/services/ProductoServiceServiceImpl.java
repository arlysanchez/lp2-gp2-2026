package pe.edu.upeu.lp2_gp2.services;


import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lp2_gp2.dto.ProductoDTO;
import pe.edu.upeu.lp2_gp2.entity.Producto;
import pe.edu.upeu.lp2_gp2.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceServiceImpl implements IProductoService {
    private final ProductoRepository productoRepository;

    public ProductoServiceServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> listarTodo() {
        return productoRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public Optional<ProductoDTO> buscarPorId(Long id) {
        return Optional.empty();
    }

    @Override
    public ProductoDTO crear(ProductoDTO p) {
        return null;
    }

    @Override
    public Optional<ProductoDTO> actualizar(Long id, ProductoDTO p) {
        return Optional.empty();
    }

    @Override
    public boolean eliminar(Long id) {
        return false;
    }

    private  ProductoDTO convertToDTO(Producto p){
        return new ProductoDTO(
                p.getIdProducto(),
                p.getNombre(),
                p.getDescripcion(),
                p.getPrecio(),
                p.getStock()
        );
    }
}
