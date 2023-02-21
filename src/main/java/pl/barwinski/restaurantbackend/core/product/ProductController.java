package pl.barwinski.restaurantbackend.core.product;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.barwinski.restaurantbackend.utils.EndpointPaths;

import java.util.List;


@RestController
@RequestMapping(EndpointPaths.BASE)
public class ProductController{

    private final ProductMapper productMapper;

    private final ProductService productService;

    public ProductController(ProductMapper productMapper, ProductService productService) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @RequestMapping(method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductEntity> projects = productService.getAll();
        return ResponseEntity.ok(productMapper.mapToDto(projects));
    }
}
