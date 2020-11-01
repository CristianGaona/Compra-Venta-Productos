package com.crisda24.market.domain.repository;

import java.util.List;
import java.util.Optional;

import com.crisda24.market.domain.Purchase;

public interface PurchaseRepository {

	public List<Purchase> getAll();
	Optional<List<Purchase>> getByClient(String clienteId);
	Purchase save(Purchase purchase);
}
