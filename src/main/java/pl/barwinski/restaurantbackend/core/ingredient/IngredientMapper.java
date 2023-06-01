package pl.barwinski.restaurantbackend.core.ingredient;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IngredientMapper {
    IngredientEntity mapToEntity(IngredientDto ingredient);
    IngredientDto mapToDto(IngredientEntity ingredientEntity);

    List<IngredientEntity> mapToEntity(List<IngredientDto> ingredients);
    List<IngredientDto> mapToDto(List<IngredientEntity> ingredientEntities);
}
