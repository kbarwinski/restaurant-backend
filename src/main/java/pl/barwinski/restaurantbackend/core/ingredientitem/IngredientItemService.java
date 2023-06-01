package pl.barwinski.restaurantbackend.core.ingredientitem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.barwinski.restaurantbackend.core.ingredient.IngredientService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class IngredientItemService {
    private final IngredientItemRepository ingredientItemRepository;
    private final IngredientService ingredientService;

    public IngredientItemEntity getById(Long id) {
            return ingredientItemRepository.findById(id).orElseThrow();
    }

    public List<IngredientItemEntity> getByProductId(Long productId) {
            return ingredientItemRepository.findByProductId(productId);
    }

    public IngredientItemEntity save(IngredientItemEntity ingredientItem) {
            return ingredientItemRepository.save(ingredientItem);
    }

    public void deleteById(Long id) {
            ingredientItemRepository.deleteById(id);
    }

    public void subtractIngredientsFromStock(Long productId){
        List<IngredientItemEntity> recipe = getByProductId(productId);

        for (IngredientItemEntity ingredientItem : recipe) {
            ingredientItem.getIngredient().setStock(
                    ingredientItem.getIngredient()
                            .getStock().subtract(ingredientItem.getQuantity()));

            ingredientService.save(ingredientItem.getIngredient());
        }
    }
}
