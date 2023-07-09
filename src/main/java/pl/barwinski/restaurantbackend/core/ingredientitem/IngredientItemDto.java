package pl.barwinski.restaurantbackend.core.ingredientitem;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Setter;
import pl.barwinski.restaurantbackend.core.ingredient.IngredientDto;
import pl.barwinski.restaurantbackend.core.ingredient.IngredientEntity;
import pl.barwinski.restaurantbackend.core.product.ProductEntity;

import java.math.BigDecimal;

public class IngredientItemDto {
    public Long id;
    public IngredientDto ingredient;
    public BigDecimal quantity;
}
