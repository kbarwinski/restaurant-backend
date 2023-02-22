package pl.barwinski.restaurantbackend.core.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Long> {

    ProductEntity save(ProductEntity product);
    Optional<ProductEntity> findById(Long id);
}
