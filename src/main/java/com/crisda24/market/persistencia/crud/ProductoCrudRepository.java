package com.crisda24.market.persistencia.crud;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.crisda24.market.persistencia.entity.Producto;

public interface ProductoCrudRepository  extends CrudRepository<Producto, Integer>{

	// Query Methods
	//@Query(value = "SELECT * FROM productos WHERE  id_categoria = ?", nativeQuery = true )
	List<Producto> findByIdCategoriaOrderByNombreAsc (int idCategoria);
	Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}
