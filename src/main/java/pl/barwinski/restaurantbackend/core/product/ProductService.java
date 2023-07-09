package pl.barwinski.restaurantbackend.core.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService{
    private final ProductRepository productRepository;

    public ProductEntity save(ProductEntity product) {
        if(product.getRecipe() == null)
            product.setRecipe(List.of());
        else
            product.getRecipe().forEach(ingredientItem -> {
                ingredientItem.setProduct(product);
            });

        return productRepository.save(product);
    }

    public Page<ProductEntity> getByNameContainingAndPriceBetween(String name, Optional<BigDecimal> minPrice, Optional<BigDecimal> maxPrice, Pageable pageable){

        BigDecimal min = minPrice.orElse(BigDecimal.valueOf(0));
        BigDecimal max = maxPrice.orElse(BigDecimal.valueOf(Double.MAX_VALUE));

        return productRepository.findByNameContainingAndPriceBetween(name, min, max, pageable);
    }


    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public ProductEntity update(Long id, ProductEntity product) {
        ProductEntity productToUpdate = productRepository.findById(id).orElseThrow();

        productToUpdate.setName(product.getName());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setCategory(product.getCategory());

        product.getRecipe().forEach(ingredientItem -> {
            ingredientItem.setProduct(productToUpdate);
        });

        productToUpdate.getRecipe().clear();
        productToUpdate.getRecipe().addAll(product.getRecipe());

        return productRepository.save(productToUpdate);
    }

    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    public Page<ProductEntity> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<ProductEntity> findAllWithEagerRecipe(Pageable page) {
        return productRepository.findAll(page);
    }

    public ProductEntity getById(long productId) {
        return productRepository.findById(productId).orElseThrow();
    }
}
