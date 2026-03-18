package pe.edu.upeu.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import pe.edu.upeu.dtos.ProductoRequest;
import pe.edu.upeu.dtos.ProductoResponse;
import pe.edu.upeu.entities.ProductoEntity;
import pe.edu.upeu.errors.ProductoNotFoundException;
import pe.edu.upeu.mappers.ProductoMapper;
import pe.edu.upeu.repositories.ProductoRepository;

import java.util.List;

@ApplicationScoped
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    public ProductoService(ProductoRepository productoRepository, ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
    }

    public List<ProductoResponse> listar() {
        return productoRepository.listarTodos()
                .stream()
                .map(productoMapper::toResponse)
                .toList();
    }

    public ProductoResponse obtenerPorId(Long id) {
        ProductoEntity entity = productoRepository.buscarPorId(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        return productoMapper.toResponse(entity);
    }

    @Transactional
    public ProductoResponse registrar(ProductoRequest request) {
        ProductoEntity entity = productoMapper.toEntity(request);
        productoRepository.persist(entity);
        return productoMapper.toResponse(entity);
    }

    @Transactional
    public ProductoResponse actualizar(Long id, ProductoRequest request) {
        ProductoEntity entity = productoRepository.buscarPorId(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        productoMapper.updateEntity(entity, request);
        return productoMapper.toResponse(entity);
    }

    @Transactional
    public void eliminar(Long id) {
        ProductoEntity entity = productoRepository.buscarPorId(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        productoRepository.delete(entity);
    }

    public List<ProductoResponse> buscarPorNombre(String nombre) {
        return productoRepository.buscarPorNombre(nombre)
                .stream()
                .map(productoMapper::toResponse)
                .toList();
    }
}
