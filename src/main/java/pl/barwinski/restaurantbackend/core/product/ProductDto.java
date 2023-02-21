package pl.barwinski.restaurantbackend.core.product;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;


public class ProductDto {
    public String name;

    public String description;

    public String imageUrl;

    public BigDecimal price;

}
