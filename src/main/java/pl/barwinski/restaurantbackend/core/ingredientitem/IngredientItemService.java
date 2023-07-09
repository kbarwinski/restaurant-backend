package pl.barwinski.restaurantbackend.core.ingredientitem;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.barwinski.restaurantbackend.core.ingredient.IngredientService;
import pl.barwinski.restaurantbackend.core.order.OrderEntity;
import pl.barwinski.restaurantbackend.core.orderitem.OrderItemEntity;

import java.math.BigDecimal;
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

    @Transactional
    public void subtractIngredientsFromStock(OrderEntity order){
        List<OrderItemEntity> orderItems = order.getOrderItems();

        for(OrderItemEntity orderItem : orderItems){
            List<IngredientItemEntity> recipe = getByProductId(orderItem.getProduct().getId());
            for (IngredientItemEntity ingredientItem : recipe) {
                ingredientItem.getIngredient().setStock(
                        ingredientItem.getIngredient()
                                .getStock()
                                .subtract(ingredientItem.getQuantity()
                                        .multiply(BigDecimal.valueOf(orderItem.getQuantity()))));

                ingredientService.save(ingredientItem.getIngredient());
            }
        }
    }
}
