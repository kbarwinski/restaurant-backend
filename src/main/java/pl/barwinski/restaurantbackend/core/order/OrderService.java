package pl.barwinski.restaurantbackend.core.order;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.id.GUIDGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.barwinski.restaurantbackend.core.address.AddressEntity;
import pl.barwinski.restaurantbackend.core.address.AddressService;
import pl.barwinski.restaurantbackend.core.ingredientitem.IngredientItemService;
import pl.barwinski.restaurantbackend.core.orderitem.OrderItemEntity;
import pl.barwinski.restaurantbackend.core.orderitem.OrderItemRequest;
import pl.barwinski.restaurantbackend.core.orderitem.OrderItemService;
import pl.barwinski.restaurantbackend.core.product.ProductService;
import pl.barwinski.restaurantbackend.core.user.UserEntity;
import pl.barwinski.restaurantbackend.core.user.UserService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderItemService orderItemService;
    private final IngredientItemService ingredientItemService;

    public OrderEntity save(OrderEntity order) {
        return orderRepository.save(order);
    }

    public OrderEntity getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    public OrderEntity getOrder(UUID publicId) {
        return orderRepository.findByPublicId(publicId).orElseThrow();
    }

    public Page<OrderEntity> getOrders(Pageable pageable) {
        return orderRepository.findAllByOrderByOrderDateDesc(pageable);
    }

    @Transactional
    public void deleteOrder(UUID publicId) {
        orderRepository.deleteByPublicId(publicId);
    }

    public OrderEntity updateOrderStatus(UUID publicId, OrderEntity.OrderStatus status) {
        OrderEntity order = getOrder(publicId);

        if(order.getStatus() == OrderEntity.OrderStatus.COMPLETED || order.getStatus() == OrderEntity.OrderStatus.CANCELLED)
            throw new IllegalStateException("Order status cannot be changed");

        if(status == OrderEntity.OrderStatus.COMPLETED || status == OrderEntity.OrderStatus.CANCELLED){
            order.setCompletionDate(LocalDateTime.now());
            if(status == OrderEntity.OrderStatus.COMPLETED)
                ingredientItemService.subtractIngredientsFromStock(order);
        }

        order.setStatus(status);
        return save(order);
    }

    @Transactional
    public OrderEntity createClientOrder(String email, OrderRequest orderRequest){
        OrderEntity order = new OrderEntity();
        order.setPublicId(java.util.UUID.randomUUID());

        UserEntity user = userService.getByEmail(email);
        AddressEntity address = user.getAddresses().get(0);

        order.setUser(user);
        order.setAddress(address);

        ArrayList<OrderItemEntity> orderItemEntities = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (OrderItemRequest orderItemRequest : orderRequest.orderItems){
            OrderItemEntity orderItem = orderItemService.createOrderItem(orderItemRequest);
            orderItem.setOrder(order);

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
