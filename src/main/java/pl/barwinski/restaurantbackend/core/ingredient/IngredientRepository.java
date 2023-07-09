package pl.barwinski.restaurantbackend.core.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {
    List<IngredientEntity> findAllByOrderByStockAsc();
}
