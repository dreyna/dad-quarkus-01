package pe.edu.upeu.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import pe.edu.upeu.dtos.ProductoRequest;
import pe.edu.upeu.dtos.ProductoResponse;
import pe.edu.upeu.entities.ProductoEntity;

@ApplicationScoped
public class ProductoMapper {
    public ProductoEntity toEntity(ProductoRequest request) {
        ProductoEntity entity = new ProductoEntity();
        entity.setNombre(request.getNombre());
        entity.setDescripcion(request.getDescripcion());
        entity.setPrecio(request.getPrecio());
        entity.setStock(request.getStock());
        return entity;
    }

    public ProductoResponse toResponse(ProductoEntity entity) {
        return new ProductoResponse(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getPrecio(),
                entity.getStock()
        );
    }

    public void updateEntity(ProductoEntity entity, ProductoRequest request) {
        entity.setNombre(request.getNombre());
        entity.setDescripcion(request.getDescripcion());
        entity.setPrecio(request.getPrecio());
        entity.setStock(request.getStock());
    }
}
