package pl.barwinski.restaurantbackend.core.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductEntity save(ProductEntity product) {
        return productRepository.save(product);
    }

    public Page<ProductEntity> getByNameContainingAndPriceBetween(String name, Optional<BigDecimal> minPrice, Optional<BigDecimal> maxPrice, Pageable pageable){

        BigDecimal min = minPrice.orElse(BigDecimal.valueOf(0));
        BigDecimal max = maxPrice.orElse(BigDecimal.valueOf(1000000));

        return productRepository.findByNameContainingAndPriceBetween(name, min, max, pageable);
    }

    public ProductEntity getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

}
