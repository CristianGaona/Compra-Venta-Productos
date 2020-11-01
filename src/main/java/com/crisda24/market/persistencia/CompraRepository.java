package com.crisda24.market.persistencia;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crisda24.market.domain.Purchase;
import com.crisda24.market.domain.repository.PurchaseRepository;
import com.crisda24.market.persistencia.crud.CompraCrudRepository;
import com.crisda24.market.persistencia.entity.Compra;
import com.crisda24.market.persistencia.mapper.PurchaseMapper;

@Repository
public class CompraRepository  implements PurchaseRepository{

	@Autowired
	private CompraCrudRepository compraCrudRepository;
	
	@Autowired
	private PurchaseMapper mapper;
	
	
	@Override
	public List<Purchase> getAll() {
		
		return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
	}

	@Override
	public Optional<List<Purchase>> getByClient(String clienteId) {
		
		return compraCrudRepository.findByIdCliente(clienteId).map(compras -> mapper.toPurchases(compras));
	}

	@Override
	public Purchase save(Purchase purchase) {
		Compra compra = mapper.toCompra(purchase);
		compra.getProductos().forEach(productos -> productos.setCompra(compra));
		return mapper.toPurchase(compraCrudRepository.save(compra));
	}

}
