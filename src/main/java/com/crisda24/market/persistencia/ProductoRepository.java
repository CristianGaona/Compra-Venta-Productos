package com.crisda24.market.persistencia;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crisda24.market.domain.Product;
import com.crisda24.market.domain.repository.ProductRepository;
import com.crisda24.market.persistencia.crud.ProductoCrudRepository;
import com.crisda24.market.persistencia.entity.Producto;
import com.crisda24.market.persistencia.mapper.ProductMapper;

@Repository
public class ProductoRepository implements ProductRepository {

	@Autowired
	private ProductoCrudRepository productoCrudRepository;
	@Autowired
	private ProductMapper mapper;
	
	@Override
	public List<Product> getAll(){
		List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
		return mapper.toProducts(productos);
	}
	
	
	/*public List<Producto> getByCategoria(int idCategoria){
		return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
		
	}*/
	

	/*public Optional<List<Producto>> getEscasos(int cantidad){
		return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
	}*/
	

	/*public Optional<Producto> getProducto(int idProducto){
		return productoCrudRepository.findById(idProducto);
	}*/
	

	/*public Producto save(Producto producto) {
		return productoCrudRepository.save(producto);
		
	}*/
	
    @Override
	public void delete(int productId) {
		productoCrudRepository.deleteById(productId);
	}

	@Override
	public Optional<List<Product>> getByCategory(int categoryId) {
		List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
		return Optional.of(mapper.toProducts(productos));
	}

	@Override
	public Optional<List<Product>> getScarseProducts(int quantity) {
		Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
		return productos.map(prods -> mapper.toProducts(prods));
	}

	@Override
	public Optional<Product> getProduct(int productId) {
		return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
	
	}

	@Override
	public Product save(Product product) {
		Producto producto = mapper.toProducto(product);
		return mapper.toProduct(productoCrudRepository.save(producto));
		
	}
}
