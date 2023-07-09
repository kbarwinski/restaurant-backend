package pl.barwinski.restaurantbackend.core.ingredient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    public IngredientEntity getById(Long id) {
        return ingredientRepository.findById(id).orElseThrow();
    }
    public List<IngredientEntity> getAll() {
        return ingredientRepository.findAll();
    }

    public List<IngredientEntity> getAllByStockAsc() {
        return ingredientRepository.findAllByOrderByStockAsc();
    }

    public IngredientEntity save(IngredientEntity ingredient) {
        return ingredientRepository.save(ingredient);
    }
    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }

    public IngredientEntity update(Long id, IngredientEntity ingredient) {
        IngredientEntity ingredientToUpdate = ingredientRepository.findById(id).orElseThrow();
        ingredientToUpdate.setName(ingredient.getName());
        ingredientToUpdate.setCategory(ingredient.getCategory());
        ingredientToUpdate.setMeasure(ingredient.getMeasure());
        ingredientToUpdate.setStock(ingredient.getStock());
        return ingredientRepository.save(ingredientToUpdate);
    }
}
