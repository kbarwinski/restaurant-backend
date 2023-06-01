package pl.barwinski.restaurantbackend.core.orderitem;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Setter;
import pl.barwinski.restaurantbackend.core.order.OrderDto;
import pl.barwinski.restaurantbackend.core.order.OrderEntity;
import pl.barwinski.restaurantbackend.core.product.ProductDto;
import pl.barwinski.restaurantbackend.core.product.ProductEntity;

import java.math.BigDecimal;

public class OrderItemDto {
    public Long id;

    public BigDecimal price;
    public int quantity;

    public ProductDto product;
}
