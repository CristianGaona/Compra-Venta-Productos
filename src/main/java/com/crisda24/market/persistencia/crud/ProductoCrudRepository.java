package com.crisda24.market.persistencia.crud;

import org.springframework.data.repository.CrudRepository;

import com.crisda24.market.persistencia.entity.Producto;

public interface ProductoCrudRepository  extends CrudRepository<Producto, Integer>{

}
