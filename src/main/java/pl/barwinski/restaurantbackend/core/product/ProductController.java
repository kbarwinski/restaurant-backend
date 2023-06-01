package pl.barwinski.restaurantbackend.core.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping(EndpointPaths.BASE)
@Tag(name = "Product")
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;
    @GetMapping(path = EndpointPaths.PRODUCT)
    @Operation(summary = "Get all products", description = "Retrieve a list of all products")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved products")
    public ResponseEntity<List<ProductDto>> getProducts(
            @RequestParam(defaultValue = "") @Parameter(description = "Product name") String name,
            @RequestParam(defaultValue = "0") @Parameter(description = "Page number") int page,
            @RequestParam(defaultValue = "3") @Parameter(description = "Page size") int size,
            @RequestParam(required = false) @Parameter(description = "Minimum price") BigDecimal minPrice,
            @RequestParam(required = false) @Parameter(description = "Maximum price") BigDecimal maxPrice,
            @RequestParam(defaultValue = "price,desc") @Parameter(description = "Sorting criteria") String[] sort) {

        List<Order> orders = new ArrayList<>();

        if (sort[0].contains(",")) {
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Order(Sort.Direction.fromString(_sort[1]), _sort[0]));
            }
        } else {
            orders.add(new Order(Sort.Direction.fromString(sort[1]), sort[0]));
        }

        Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

        Page<ProductEntity> products = productService.getByNameContainingAndPriceBetween(name, Optional.ofNullable(minPrice), Optional.ofNullable(maxPrice), pagingSort);
        List<ProductDto> productDtos = productMapper.mapToDto(products.toList());

        return ResponseEntity.ok(productDtos);
    }
}
