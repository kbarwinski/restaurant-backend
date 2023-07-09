package pl.barwinski.restaurantbackend.core.ingredient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.barwinski.restaurantbackend.utils.EndpointPaths;

import java.util.List;

@RestController
@RequestMapping(path = EndpointPaths.BASE + EndpointPaths.EMPLOYEE)
@Tag(name = "Ingredients")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;
    private final IngredientMapper ingredientMapper;

    @GetMapping(path = EndpointPaths.INGREDIENT + "/{id}")
    @Operation(summary = "Get ingredient by ID", description = "Retrieve an ingredient by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved ingredient"),
            @ApiResponse(responseCode = "404", description = "Ingredient not found")
    })
    public ResponseEntity<IngredientDto> getIngredient(@PathVariable Long id) {
        IngredientEntity ingredient = ingredientService.getById(id);
        IngredientDto ingredientDto = ingredientMapper.mapToDto(ingredient);
        return ResponseEntity.ok(ingredientDto);
    }

    @GetMapping(path = EndpointPaths.INGREDIENT)
    @Operation(summary = "Get all ingredients", description = "Retrieve a list of all ingredients")
    public ResponseEntity<List<IngredientDto>> getAllIngredients() {
        List<IngredientEntity> ingredients = ingredientService.getAll();
        List<IngredientDto> ingredientDtos = ingredientMapper.mapToDto(ingredients);
        return ResponseEntity.ok(ingredientDtos);
    }

    @GetMapping(path = EndpointPaths.INGREDIENT + "/stock-asc")
    @Operation(summary = "Get all ingredients sorted by stock in ascending order", description = "Retrieve a list of all ingredients sorted by stock in ascending order")
    public ResponseEntity<List<IngredientDto>> getAllIngredientsByStockAsc() {
        List<IngredientEntity> ingredients = ingredientService.getAllByStockAsc();
        List<IngredientDto> ingredientDtos = ingredientMapper.mapToDto(ingredients);
        return ResponseEntity.ok(ingredientDtos);
    }

    @PutMapping(path = EndpointPaths.INGREDIENT + "/{id}")
    @Operation(summary = "Update ingredient by ID", description = "Update an ingredient by its ID")
    @ApiResponse(responseCode = "200", description = "Ingredient updated")
    public ResponseEntity<IngredientDto> updateIngredient(@PathVariable Long id, @RequestBody IngredientDto ingredientDto) {
        IngredientEntity ingredient = ingredientMapper.mapToEntity(ingredientDto);
        IngredientEntity updatedIngredient = ingredientService.update(id,ingredient);
        IngredientDto updatedIngredientDto = ingredientMapper.mapToDto(updatedIngredient);
        return ResponseEntity.ok(updatedIngredientDto);
    }

    @PostMapping(path = EndpointPaths.INGREDIENT)
    @Operation(summary = "Create a new ingredient", description = "Create a new ingredient")
    @ApiResponse(responseCode = "201", description = "Ingredient created")
    public ResponseEntity<IngredientDto> createIngredient(@RequestBody IngredientDto ingredientDto) {
        IngredientEntity ingredient = ingredientMapper.mapToEntity(ingredientDto);
        IngredientEntity createdIngredient = ingredientService.save(ingredient);
        IngredientDto createdIngredientDto = ingredientMapper.mapToDto(createdIngredient);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdIngredientDto);
    }

    @DeleteMapping(path = EndpointPaths.INGREDIENT + "/{id}")
    @Operation(summary = "Delete ingredient by ID", description = "Delete an ingredient by its ID")
    @ApiResponse(responseCode = "204", description = "Ingredient deleted")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

