package com.crisda24.market.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crisda24.market.domain.Purchase;
import com.crisda24.market.domain.repository.PurchaseRepository;

@Service
public class PurchaseService {

	@Autowired
	private PurchaseRepository purchaseRepository;
	
	public List<Purchase> getAll(){
		return purchaseRepository.getAll();
	}
	
	public Optional<List<Purchase>> getByClient(String clientId){
		return purchaseRepository.getByClient(clientId);
	}
	
	 public Purchase save(Purchase purchase) {
	        return purchaseRepository.save(purchase);
	    }
	
}
