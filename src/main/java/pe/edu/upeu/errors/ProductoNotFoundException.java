package pe.edu.upeu.errors;

public class ProductoNotFoundException extends RuntimeException{
    public ProductoNotFoundException(Long id){
        super("No existe el producto con el id: " + id);
    }
}
