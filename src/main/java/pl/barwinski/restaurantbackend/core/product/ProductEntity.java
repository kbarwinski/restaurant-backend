package pl.barwinski.restaurantbackend.core.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import pl.barwinski.restaurantbackend.core.ingredientitem.IngredientItemEntity;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@Table(name = "products")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity {

    public enum Category{
        MAIN_DISH, SOUP, SIDE_DISH, DESSERT, BEVERAGE, ALC_BEVERAGE
    }

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotNull
    @Length(max = 255)
    private String name;

    @NotBlank
    @NotNull
    @Length(max = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    @NotBlank
    @NotNull
    @Length(max = 255)
    private String imageUrl;

    @NotNull
    @Min(0)
    private BigDecimal price;

    @NotNull
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IngredientItemEntity> recipe;
}