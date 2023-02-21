package pl.barwinski.restaurantbackend.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pl.barwinski.restaurantbackend.core.product.ProductEntity;
import pl.barwinski.restaurantbackend.core.product.ProductService;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Configuration
public class DefaultDataSeeder implements CommandLineRunner {

    private final ProductService productService;

    private void buildMenu()
    {
        ProductEntity mainDish1 = productService.save(
                ProductEntity.builder()
                        .name("Kotlet schabowy")
                        .category(ProductEntity.Category.MAIN_DISH)
                        .description("Kotlet schabowy podany z ziemniakami i zestawem surowek.")
                        .imageUrl("https://www.kwestiasmaku.com/sites/v123.kwestiasmaku.com/files/schabowe_01.jpg")
                        .price(BigDecimal.valueOf(32.99))
                        .build());

        ProductEntity mainDish2 = productService.save(
                ProductEntity.builder()
                        .name("Kotlet mielony")
                        .category(ProductEntity.Category.MAIN_DISH)
                        .description("Kotlet mielony podany z ziemniakami i zestawem surowek.")
                        .imageUrl("https://cdn.aniagotuje.com/pictures/articles/2020/03/2463382-v-1500x1500.jpg")
                        .price(BigDecimal.valueOf(28.99))
                        .build());

        ProductEntity mainDish3 = productService.save(
                ProductEntity.builder()
                        .name("Kotlet de volaille")
                        .category(ProductEntity.Category.MAIN_DISH)
                        .description("Kotlet de volaille podany z ziemniakami i zestawem surowek.")
                        .imageUrl("https://bi.im-g.pl/im/99/c5/18/z25975449AMP,Kotlet-de-volaille-z-maslem-i-czosnkiem---klasyk-n.jpg")
                        .price(BigDecimal.valueOf(35.99))
                        .build());
    }

    @Override
    public void run(String... args) {
        buildMenu();
    }
}
