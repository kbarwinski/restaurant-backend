package pl.barwinski.restaurantbackend.core.ingredientitem;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IngredientItemMapper {
    IngredientItemEntity mapToEntity(IngredientItemDto ingredientItem);
    IngredientItemDto mapToDto(IngredientItemEntity ingredientItemEntity);
    List<IngredientItemEntity> mapToEntity(List<IngredientItemDto> ingredientItems);
    List<IngredientItemDto> mapToDto(List<IngredientItemEntity> ingredientItemEntities);

}
