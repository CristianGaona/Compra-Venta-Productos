package com.crisda24.market.persistencia.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ComprasProductoPK implements Serializable {

	@Column(name = "id_compra")
	private Integer idCompra;
	
	@Column(name = "id_producto")
	private Integer idProducto;
	
	private static final long serialVersionUID = 1L;

	public Integer getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Integer idCompra) {
		this.idCompra = idCompra;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	
	
	

}
