package pl.barwinski.restaurantbackend.core.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Long> {

    ProductEntity save(ProductEntity product);
    Optional<ProductEntity> findById(Long id);
    Page<ProductEntity> findByNameContainingAndPriceBetween(String name, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);
    Page<ProductEntity> findByNameContainingAndPriceBetweenAndCategoryEquals(String name, BigDecimal minPrice, BigDecimal maxPrice,
                                                                             ProductEntity.Category category,Pageable pageable);
    @Query(value = "SELECT max(price) FROM products", nativeQuery = true)
    BigDecimal findMaxProductPrice();

    List<ProductEntity> findAll();

    void deleteById(Long id);

    @EntityGraph(attributePaths = {"recipe"})
    Page<ProductEntity> findAll(Pageable pageable);
}
