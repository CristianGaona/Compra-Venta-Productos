package com.crisda24.market.persistencia.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.crisda24.market.domain.Category;
import com.crisda24.market.persistencia.entity.Categoria;

@Mapper(componentModel = "spring" )
public interface CategoryMapper {
	
	@Mappings({
		@Mapping(source = "idCategoria", target = "categoryId"),
		@Mapping(source = "descripcion", target = "category"),
		@Mapping(source = "estado", target = "active"),
		
	})
	Category toCategory(Categoria categoria);
	
	
	@InheritInverseConfiguration
	@Mapping(target = "productos", ignore = true)
	Categoria toCategoria(Category category);
}
