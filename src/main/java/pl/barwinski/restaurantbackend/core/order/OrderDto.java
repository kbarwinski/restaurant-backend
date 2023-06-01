package pl.barwinski.restaurantbackend.core.order;

import pl.barwinski.restaurantbackend.core.address.AddressDto;
import pl.barwinski.restaurantbackend.core.orderitem.OrderItemDto;
import pl.barwinski.restaurantbackend.core.user.UserDto;
import pl.barwinski.restaurantbackend.core.user.UserSimpleDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderDto {
    public Long id;
    public LocalDateTime completionDate;
    public LocalDateTime orderDate;
    public BigDecimal totalPrice;
    public OrderEntity.OrderStatus status;

    public UserSimpleDto user;

    public AddressDto address;
    public ArrayList<OrderItemDto> orderItems;
}
