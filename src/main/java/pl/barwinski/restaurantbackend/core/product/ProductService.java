package pl.barwinski.restaurantbackend.core.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductEntity save(ProductEntity product) {
        return productRepository.save(product);
    }

    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    public ProductEntity getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

}
