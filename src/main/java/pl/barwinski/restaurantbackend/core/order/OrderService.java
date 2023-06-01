package pl.barwinski.restaurantbackend.core.order;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.barwinski.restaurantbackend.core.address.AddressEntity;
import pl.barwinski.restaurantbackend.core.address.AddressService;
import pl.barwinski.restaurantbackend.core.orderitem.OrderItemEntity;
import pl.barwinski.restaurantbackend.core.orderitem.OrderItemRequest;
import pl.barwinski.restaurantbackend.core.orderitem.OrderItemService;
import pl.barwinski.restaurantbackend.core.product.ProductService;
import pl.barwinski.restaurantbackend.core.user.UserEntity;
import pl.barwinski.restaurantbackend.core.user.UserService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderItemService orderItemService;
    private final AddressService addressService;
    private final ProductService productService;

    public OrderEntity save(OrderEntity order) {
        return orderRepository.save(order);
    }

    public OrderEntity getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    public Page<OrderEntity> getOrders(Pageable pageable) {
        return orderRepository.findAllByOrderByOrderDateDesc(pageable);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public OrderEntity updateOrderStatus(Long id, OrderEntity.OrderStatus status) {
        OrderEntity order = getOrder(id);
        order.setStatus(status);
        return save(order);
    }

    public OrderEntity createClientOrder(OrderRequest orderRequest){
        OrderEntity order = new OrderEntity();

        UserEntity user = userService.getByEmail(orderRequest.email);
        AddressEntity address = addressService.getById(orderRequest.addressId);

        order.setUser(user);
        order.setAddress(address);

        ArrayList<OrderItemEntity> orderItemEntities = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (OrderItemRequest orderItemRequest : orderRequest.orderItems) {
            OrderItemEntity orderItem = orderItemService.createOrderItem(orderItemRequest);

            orderItemEntities.add(orderItem);
            totalPrice = totalPrice.add(orderItem.getPrice());
        }

        order.setOrderItems(orderItemEntities);
        order.setTotalPrice(totalPrice);

        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderEntity.OrderStatus.PENDING);

        return save(order);
    }
}
