package pe.edu.upeu.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import pe.edu.upeu.entities.ProductoEntity;

import java.util.List;
import java.util.Optional;
@ApplicationScoped
public class ProductoRepository implements PanacheRepository<ProductoEntity> {
    public List<ProductoEntity> listarTodos(){
        return listAll();
    }
    public Optional<ProductoEntity> buscarPorId(Long id) {
        return findByIdOptional(id);
    }

    public List<ProductoEntity> buscarPorNombre(String nombre) {
        return find("lower(nombre) like lower(?1)", "%" + nombre + "%").list();
    }
}
