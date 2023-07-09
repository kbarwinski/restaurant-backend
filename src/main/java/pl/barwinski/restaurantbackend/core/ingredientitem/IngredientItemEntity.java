package pl.barwinski.restaurantbackend.core.ingredientitem;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import pl.barwinski.restaurantbackend.core.ingredient.IngredientEntity;
import pl.barwinski.restaurantbackend.core.product.ProductEntity;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@Table(name = "ingredientitems")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IngredientItemEntity {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ingredient_id")
    private IngredientEntity ingredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @NotNull
    @Min(0)
    private BigDecimal quantity;
}
