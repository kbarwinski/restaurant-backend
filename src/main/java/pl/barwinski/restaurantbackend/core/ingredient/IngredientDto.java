package pl.barwinski.restaurantbackend.core.ingredient;

import java.math.BigDecimal;

public class IngredientDto {
    public Long id;
    public String name;
    public IngredientEntity.Category category;
    public IngredientEntity.Measure measure;
    public BigDecimal stock;
}
