package pl.barwinski.restaurantbackend.core.order;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderDto mapToDto(OrderEntity orderEntity);
    OrderEntity mapToEntity(OrderDto orderDto);
    List<OrderDto> mapToDto(List<OrderEntity> orderEntities);
    List<OrderEntity> mapToEntity(List<OrderDto> orderDtos);
}
