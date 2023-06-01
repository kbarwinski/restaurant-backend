package pl.barwinski.restaurantbackend.core.order;

import pl.barwinski.restaurantbackend.core.orderitem.OrderItemRequest;

import java.util.List;

public class OrderRequest {
    public String email;
    public long addressId;
    public List<OrderItemRequest> orderItems;

}
