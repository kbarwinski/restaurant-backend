package pl.barwinski.restaurantbackend.core.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<OrderEntity, Long> {
    Page<OrderEntity> findAllByOrderByOrderDateDesc(Pageable pageable);
    Page<OrderEntity> findAllByUserId(Long userId, Pageable pageable);

    OrderEntity save(OrderEntity order);

    Optional<OrderEntity> findById(Long id);

    void deleteById(Long id);
}
