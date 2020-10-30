package com.crisda24.market.persistencia;

import java.util.List;

import com.crisda24.market.persistencia.crud.ProductoCrudRepository;
import com.crisda24.market.persistencia.entity.Producto;

public class ProductoRepository {

	private ProductoCrudRepository productoCrudRepository;
	
	public List<Producto> getAll(){
		return (List<Producto>) productoCrudRepository.findAll();
	

	}
}
