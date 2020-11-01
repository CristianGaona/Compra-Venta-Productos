package com.crisda24.market.persistencia.crud;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.crisda24.market.persistencia.entity.Compra;

public interface CompraCrudRepository extends CrudRepository<Compra, Integer>{

	Optional<List<Compra>> findByIdCliente(String idCliente);
}
