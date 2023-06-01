package pl.barwinski.restaurantbackend.core.ingredientitem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientItemRepository extends JpaRepository<IngredientItemEntity, Long> {
    List<IngredientItemEntity> findByProductId(Long productId);
}
