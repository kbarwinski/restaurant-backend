package pl.barwinski.restaurantbackend.core.ingredient;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import pl.barwinski.restaurantbackend.core.ingredientitem.IngredientItemEntity;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@Table(name = "ingredients")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IngredientEntity {
    public enum Category{
        MEAT,
        VEGETABLE,
        FRUIT,
        DAIRY,
        GRAIN,
        SPICE,
        SAUCE,
        OTHER
    }
    public enum Measure{
        GRAM,
        KILOGRAM,
        LITER,
        MILLILITER,
        PIECE
    }

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Measure measure;

    @NotNull
    private BigDecimal stock;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<IngredientItemEntity> ingredientItems;
}
