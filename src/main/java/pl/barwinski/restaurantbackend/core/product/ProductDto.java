package pl.barwinski.restaurantbackend.core.product;

import pl.barwinski.restaurantbackend.core.ingredientitem.IngredientItemDto;

import java.math.BigDecimal;
import java.util.List;

public class ProductDto {
    public Long id;
    public String name;
    public String description;
    public String imageUrl;
    public BigDecimal price;
    public String category;
    public List<IngredientItemDto> recipe;
}
