package pl.barwinski.restaurantbackend.core.orderitem;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    OrderItemDto mapToDto(OrderItemEntity orderItemEntity);
    OrderItemEntity mapToEntity(OrderItemDto orderItemDto);
    List<OrderItemDto> mapToDto(List<OrderItemEntity> orderItems);
    List<OrderItemEntity> mapToEntity(List<OrderItemDto> orderItemDtos);
}
