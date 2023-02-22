package pl.barwinski.restaurantbackend.core.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductEntity save(ProductEntity product) {
        return productRepository.save(product);
    }

    public Page<ProductEntity> getAll() {
        return productRepository.findAll(PageRequest.of(0,1));
    }

    public ProductEntity getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

}
