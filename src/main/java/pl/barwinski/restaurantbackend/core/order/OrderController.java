package pl.barwinski.restaurantbackend.core.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.barwinski.restaurantbackend.utils.EndpointPaths;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = EndpointPaths.BASE)
@RequiredArgsConstructor
@Tag(name = "Order")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;


    @GetMapping(path = EndpointPaths.EMPLOYEE + EndpointPaths.ORDER + "/{id}")
    @Operation(summary = "Get order by ID", description = "Retrieve an order by its ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved order")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long id) {
        OrderEntity order = orderService.getOrder(id);

        OrderDto orderDto = orderMapper.mapToDto(order);
        return ResponseEntity.ok(orderDto);
    }

    @GetMapping(path = EndpointPaths.CLIENT + EndpointPaths.ORDER + "/{uuid}")
    @Operation(summary = "Get order by UUID", description = "Retrieve an order by its UUID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved order")
    public ResponseEntity<OrderDto> getOrderByUUID(@PathVariable UUID uuid) {
        OrderEntity order = orderService.getOrder(uuid);

        OrderDto orderDto = orderMapper.mapToDto(order);
        return ResponseEntity.ok(orderDto);
    }

    @GetMapping(path = EndpointPaths.EMPLOYEE + EndpointPaths.ORDER)
    @Operation(summary = "Get all orders", description = "Retrieve a list of all orders")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved orders")
    public ResponseEntity<List<OrderDto>> getOrders(Pageable pageable) {
        Page<OrderEntity> orders = orderService.getOrders(pageable);

        List<OrderDto> orderDtos = orderMapper.mapToDto(orders.toList());
        return ResponseEntity.ok(orderDtos);
    }

    @PostMapping(path = EndpointPaths.CLIENT + EndpointPaths.ORDER)
    @Operation(summary = "Create a new order", description = "Create a new order")
    @ApiResponse(responseCode = "201", description = "Order created")
    public ResponseEntity<OrderDto> createClientOrder(@RequestBody OrderRequest orderRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        OrderEntity createdOrder = orderService.createClientOrder(email, orderRequest);

        OrderDto orderDto = orderMapper.mapToDto(createdOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
    }

    @PutMapping(path = EndpointPaths.EMPLOYEE + EndpointPaths.ORDER +"/{publicId}/status")
    @Operation(summary = "Update order status", description = "Update the status of an order")
    @ApiResponse(responseCode = "200", description = "Order status updated")
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable UUID publicId, @RequestBody String status) {
        OrderEntity updatedOrder = orderService.updateOrderStatus(publicId, OrderEntity.OrderStatus.valueOf(status));

        OrderDto orderDto = orderMapper.mapToDto(updatedOrder);
        return ResponseEntity.ok(orderDto);
    }

    @DeleteMapping(path = EndpointPaths.EMPLOYEE + EndpointPaths.ORDER + "/{publicId}")
    @Operation(summary = "Delete order", description = "Delete an order by its ID")
    @ApiResponse(responseCode = "204", description = "Order deleted")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID publicId) {
        orderService.deleteOrder(publicId);
        return ResponseEntity.noContent().build();
    }
}
