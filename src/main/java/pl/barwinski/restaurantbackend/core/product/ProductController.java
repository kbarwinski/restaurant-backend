package pl.barwinski.restaurantbackend.core.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.barwinski.restaurantbackend.utils.EndpointPaths;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(EndpointPaths.PRODUCT)
public class ProductController {

    private final ProductMapper productMapper;

    private final ProductService productService;

    public ProductController(ProductMapper productMapper, ProductService productService) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ProductDto>> getProducts(
            @RequestParam(defaultValue =  "") String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(defaultValue = "price,desc") String[] sort) {

        List<Order> orders = new ArrayList<>();

        if (sort[0].contains(",")) {
            // will sort more than 2 fields
            // sortOrder="field, direction"
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Order(Sort.Direction.fromString(_sort[1]), _sort[0]));
            }
        } else {
            // sort=[field, direction]
            orders.add(new Order(Sort.Direction.fromString(sort[1]), sort[0]));
        }

        Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

        Page<ProductEntity> projects = productService.getByNameContainingAndPriceBetween(name, Optional.ofNullable(minPrice), Optional.ofNullable(maxPrice), pagingSort);

        return ResponseEntity.ok(productMapper.mapToDto(projects.toList()));
    }
}
