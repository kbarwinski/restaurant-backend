package pl.barwinski.restaurantbackend.core.orderitem;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.barwinski.restaurantbackend.core.product.ProductEntity;
import pl.barwinski.restaurantbackend.core.product.ProductService;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    public OrderItemEntity save(OrderItemEntity orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public OrderItemEntity getOrderItem(Long id) {
        return orderItemRepository.findById(id).orElseThrow();
    }

    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }

    @Transactional
    public OrderItemEntity createOrderItem(OrderItemRequest request){
        ProductEntity product = productService.getById(request.productId);

        OrderItemEntity orderItem = new OrderItemEntity();

        orderItem.setProduct(product);
        orderItem.setQuantity(request.quantity);
        orderItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.quantity)));

        return save(orderItem);
    }
}
