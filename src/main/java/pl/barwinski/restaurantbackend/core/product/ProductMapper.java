package pl.barwinski.restaurantbackend.core.product;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper{
    ProductDto mapToDto(ProductEntity project);

    List<ProductDto> mapToDto(List<ProductEntity> projects);

    ProductEntity mapToEntity(ProductDto productDto);
}
