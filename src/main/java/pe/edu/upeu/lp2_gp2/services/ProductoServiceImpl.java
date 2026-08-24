package pe.edu.upeu.lp2_gp2.services;


import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.lp2_gp2.dto.ProductoDTO;
import pe.edu.upeu.lp2_gp2.entity.Producto;
import pe.edu.upeu.lp2_gp2.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements IProductoService {
    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
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
    @Transactional(readOnly = true)
    public Optional<ProductoDTO> buscarPorId(Long id) {
        return productoRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    @Transactional
    public ProductoDTO crear(ProductoDTO p) {
        Producto pro = new Producto();
        pro.setNombre(p.nombre());
        pro.setDescripcion(p.descripcion());
        pro.setPrecio(p.precio());
        pro.setStock(p.stock());
        return convertToDTO(productoRepository.save(pro));
    }

    @Override
    @Transactional
    public Optional<ProductoDTO> actualizar(Long id, ProductoDTO p) {
        return productoRepository.findById(id).map(pro->{
            pro.setNombre(p.nombre());
            pro.setDescripcion(p.descripcion());
            pro.setPrecio(p.precio());
            pro.setStock(p.stock());
            return convertToDTO(productoRepository.save(pro));
        });
    }

    @Override
    @Transactional
    public boolean eliminar(Long id) {
       if(productoRepository.existsById(id)){
           productoRepository.deleteById(id);
           return true;
       }
       return  false;
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
