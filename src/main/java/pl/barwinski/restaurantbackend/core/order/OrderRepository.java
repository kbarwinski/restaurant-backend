package pl.barwinski.restaurantbackend.core.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<OrderEntity, Long> {
    Page<OrderEntity> findAllByOrderByOrderDateDesc(Pageable pageable);

    OrderEntity save(OrderEntity order);

    Optional<OrderEntity> findById(Long id);

    Optional<OrderEntity> findByPublicId(UUID publicId);

    void deleteByPublicId(UUID publicId);
}
