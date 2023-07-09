package pl.barwinski.restaurantbackend.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pl.barwinski.restaurantbackend.core.address.AddressEntity;
import pl.barwinski.restaurantbackend.core.address.AddressService;
import pl.barwinski.restaurantbackend.core.contact.ContactEntity;
import pl.barwinski.restaurantbackend.core.contact.ContactService;
import pl.barwinski.restaurantbackend.core.ingredient.IngredientEntity;
import pl.barwinski.restaurantbackend.core.ingredient.IngredientService;
import pl.barwinski.restaurantbackend.core.ingredientitem.IngredientItemEntity;
import pl.barwinski.restaurantbackend.core.order.OrderEntity;
import pl.barwinski.restaurantbackend.core.order.OrderService;
import pl.barwinski.restaurantbackend.core.orderitem.OrderItemEntity;
import pl.barwinski.restaurantbackend.core.product.ProductEntity;
import pl.barwinski.restaurantbackend.core.product.ProductService;
import pl.barwinski.restaurantbackend.core.reservationtable.ReservationTableEntity;
import pl.barwinski.restaurantbackend.core.reservationtable.ReservationTableService;
import pl.barwinski.restaurantbackend.core.user.UserEntity;
import pl.barwinski.restaurantbackend.core.user.UserService;
import pl.barwinski.restaurantbackend.security.hashing.BCryptHashingUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Configuration
public class DefaultDataSeeder implements CommandLineRunner {

    private final ProductService productService;
    private final UserService userService;
    private final OrderService orderService;
    private final IngredientService ingredientService;
    private final ReservationTableService reservationTableService;

    private final BCryptHashingUtil bCryptHashingUtil;

    private void buildProducts(){
        IngredientEntity chicken = ingredientService.save(
                IngredientEntity.builder()
                        .name("Kurczak")
                        .category(IngredientEntity.Category.MEAT)
                        .measure(IngredientEntity.Measure.KILOGRAM)
                        .stock(BigDecimal.valueOf(10))
                        .build());

        IngredientEntity mincedMeat = ingredientService.save(
                IngredientEntity.builder()
                        .name("Mięso mielone wieprzowo-wołowe")
                        .category(IngredientEntity.Category.MEAT)
                        .measure(IngredientEntity.Measure.KILOGRAM)
                        .stock(BigDecimal.valueOf(10))
                        .build());

        IngredientEntity vegetableSalad = ingredientService.save(
                IngredientEntity.builder()
                        .name("Zestaw surówek")
                        .category(IngredientEntity.Category.VEGETABLE)
                        .measure(IngredientEntity.Measure.PIECE)
                        .stock(BigDecimal.valueOf(10))
                        .build());

        IngredientEntity potatoes = ingredientService.save(
                IngredientEntity.builder()
                        .name("Ziemniaki")
                        .category(IngredientEntity.Category.VEGETABLE)
                        .measure(IngredientEntity.Measure.KILOGRAM)
                        .stock(BigDecimal.valueOf(10))
                        .build());

        IngredientEntity butter = ingredientService.save(
                IngredientEntity.builder()
                        .name("Masło")
                        .category(IngredientEntity.Category.DAIRY)
                        .measure(IngredientEntity.Measure.KILOGRAM)
                        .stock(BigDecimal.valueOf(10))
                        .build());

        IngredientEntity tomatoes = ingredientService.save(
                IngredientEntity.builder()
                        .name("Pomidory")
                        .category(IngredientEntity.Category.VEGETABLE)
                        .measure(IngredientEntity.Measure.KILOGRAM)
                        .stock(BigDecimal.valueOf(10))
                        .build());

        IngredientEntity coffee = ingredientService.save(
                IngredientEntity.builder()
                        .name("Kawa")
                        .category(IngredientEntity.Category.OTHER)
                        .measure(IngredientEntity.Measure.KILOGRAM)
                        .stock(BigDecimal.valueOf(10))
                        .build());

        IngredientEntity milk = ingredientService.save(
                IngredientEntity.builder()
                        .name("Mleko")
                        .category(IngredientEntity.Category.DAIRY)
                        .measure(IngredientEntity.Measure.LITER)
                        .stock(BigDecimal.valueOf(10))
                        .build());

        IngredientEntity sugar = ingredientService.save(
                IngredientEntity.builder()
                        .name("Cukier")
                        .category(IngredientEntity.Category.OTHER)
                        .measure(IngredientEntity.Measure.KILOGRAM)
                        .stock(BigDecimal.valueOf(10))
                        .build());

        IngredientEntity orangeJuice = ingredientService.save(
                IngredientEntity.builder()
                        .name("Sok pomarańczowy")
                        .category(IngredientEntity.Category.OTHER)
                        .measure(IngredientEntity.Measure.LITER)
                        .stock(BigDecimal.valueOf(10))
                        .build());

        List<IngredientItemEntity> ingredientItems1 = new ArrayList<>();
        ingredientItems1.add(IngredientItemEntity.builder()
                .ingredient(chicken)
                .quantity(BigDecimal.valueOf(0.2))
                .build());
        ingredientItems1.add(IngredientItemEntity.builder()
                .ingredient(vegetableSalad)
                .quantity(BigDecimal.valueOf(1))
                .build());
        ingredientItems1.add(IngredientItemEntity.builder()
                .ingredient(potatoes)
                .quantity(BigDecimal.valueOf(0.3))
                .build());
        
        ProductEntity mainDish1 =
                ProductEntity.builder()
                        .name("Kotlet schabowy")
                        .category(ProductEntity.Category.MAIN_DISH)
                        .description("Kotlet schabowy podany z ziemniakami i zestawem surowek.")
                        .imageUrl("https://www.kwestiasmaku.com/sites/v123.kwestiasmaku.com/files/schabowe_01.jpg")
                        .price(BigDecimal.valueOf(32.99))
                        .build();

        ingredientItems1.forEach(ingredientItem -> ingredientItem.setProduct(mainDish1));
        mainDish1.setRecipe(ingredientItems1);
        productService.save(mainDish1);

        
        List<IngredientItemEntity> ingredientItems2 = new ArrayList<>();
        ingredientItems2.add(IngredientItemEntity.builder()
                .ingredient(mincedMeat)
                .quantity(BigDecimal.valueOf(0.2))
                .build());
        ingredientItems2.add(IngredientItemEntity.builder()
                .ingredient(vegetableSalad)
                .quantity(BigDecimal.valueOf(1))
                .build());
        ingredientItems2.add(IngredientItemEntity.builder()
                .ingredient(potatoes)
                .quantity(BigDecimal.valueOf(0.3))
                .build());
        
        ProductEntity mainDish2 =
                ProductEntity.builder()
                        .name("Kotlet mielony")
                        .category(ProductEntity.Category.MAIN_DISH)
                        .description("Kotlet mielony podany z ziemniakami i zestawem surowek.")
                        .imageUrl("https://cdn.aniagotuje.com/pictures/articles/2020/03/2463382-v-1500x1500.jpg")
                        .price(BigDecimal.valueOf(28.99))
                        .build();

        ingredientItems2.forEach(ingredientItem -> ingredientItem.setProduct(mainDish2));
        mainDish2.setRecipe(ingredientItems2);
        productService.save(mainDish2);

        
        List<IngredientItemEntity> ingredientItems3 = new ArrayList<>();
        ingredientItems3.add(IngredientItemEntity.builder()
                .ingredient(chicken)
                .quantity(BigDecimal.valueOf(0.2))
                .build());
        ingredientItems3.add(IngredientItemEntity.builder()
                .ingredient(vegetableSalad)
                .quantity(BigDecimal.valueOf(1))
                .build());
        ingredientItems3.add(IngredientItemEntity.builder()
                .ingredient(potatoes)
                .quantity(BigDecimal.valueOf(0.3))
                .build());
        ingredientItems3.add(IngredientItemEntity.builder()
                .ingredient(butter)
                .quantity(BigDecimal.valueOf(0.1))
                .build());
        
        ProductEntity mainDish3 =
                ProductEntity.builder()
                        .name("Kotlet de volaille")
                        .category(ProductEntity.Category.MAIN_DISH)
                        .description("Kotlet de volaille podany z ziemniakami i zestawem surowek.")
                        .imageUrl("https://bi.im-g.pl/im/99/c5/18/z25975449AMP,Kotlet-de-volaille-z-maslem-i-czosnkiem---klasyk-n.jpg")
                        .price(BigDecimal.valueOf(35.99))
                        .build();

        ingredientItems3.forEach(ingredientItem -> ingredientItem.setProduct(mainDish3));
        mainDish3.setRecipe(ingredientItems3);
        productService.save(mainDish3);


        List<IngredientItemEntity> ingredientItems4 = new ArrayList<>();
        ingredientItems4.add(IngredientItemEntity.builder()
                .ingredient(tomatoes)
                .quantity(BigDecimal.valueOf(0.2))
                .build());
        
        ProductEntity soup1 =
                ProductEntity.builder()
                        .name("Pomidorowa")
                        .category(ProductEntity.Category.SOUP)
                        .description("Tradycyjna zupa pomidorowa z dodatkiem świeżych warzyw.")
                        .imageUrl("https://cdn.aniagotuje.com/pictures/articles/2021/10/20290189-v-1500x1500.jpg")
                        .price(BigDecimal.valueOf(12.99))
                        .build();

        ingredientItems4.forEach(ingredientItem -> ingredientItem.setProduct(soup1));
        soup1.setRecipe(ingredientItems4);
        productService.save(soup1);


        List<IngredientItemEntity> ingredientItems5 = new ArrayList<>();
        ingredientItems5.add(IngredientItemEntity.builder()
                .ingredient(chicken)
                .quantity(BigDecimal.valueOf(0.2))
                .build());

        ProductEntity soup2 =
                ProductEntity.builder()
                        .name("Rosół")
                        .category(ProductEntity.Category.SOUP)
                        .description("Delikatny rosół z domowym makaronem.")
                        .imageUrl("https://www.kwestiasmaku.com/sites/v123.kwestiasmaku.com/files/rosol_0.jpg")
                        .price(BigDecimal.valueOf(10.99))
                        .build();

        ingredientItems5.forEach(ingredientItem -> ingredientItem.setProduct(soup2));
        soup2.setRecipe(ingredientItems5);
        productService.save(soup2);

        List<IngredientItemEntity> ingredientItems6 = new ArrayList<>();
        ingredientItems6.add(IngredientItemEntity.builder()
                .ingredient(orangeJuice)
                .quantity(BigDecimal.valueOf(1))
                .build());

        ProductEntity beverage1 =
                ProductEntity.builder()
                        .name("Sok pomarańczowy")
                        .category(ProductEntity.Category.BEVERAGE)
                        .description("Świeżo wyciskany sok pomarańczowy o intensywnym smaku.")
                        .imageUrl("https://villariccona.pl/wp-content/uploads/2020/04/nhf.jpg")
                        .price(BigDecimal.valueOf(7.99))
                        .build();

        ingredientItems6.forEach(ingredientItem -> ingredientItem.setProduct(beverage1));
        beverage1.setRecipe(ingredientItems6);
        productService.save(beverage1);

        List<IngredientItemEntity> ingredientItems7 = new ArrayList<>();
        ingredientItems7.add(IngredientItemEntity.builder()
                .ingredient(coffee)
                .quantity(BigDecimal.valueOf(1))
                .build());
        ingredientItems7.add(IngredientItemEntity.builder()
                .ingredient(milk)
                .quantity(BigDecimal.valueOf(0.1))
                .build());
        ingredientItems7.add(IngredientItemEntity.builder()
                .ingredient(sugar)
                .quantity(BigDecimal.valueOf(0.1))
                .build());

        ProductEntity beverage2 =
                ProductEntity.builder()
                        .name("Kawa latte")
                        .category(ProductEntity.Category.BEVERAGE)
                        .description("Delikatna kawa latte z dodatkiem mleka i pianki.")
                        .imageUrl("https://www.mojegotowanie.pl/media/cache/big/uploads/media/recipe/0002/29/caffe-latte_1.jpeg")
                        .price(BigDecimal.valueOf(9.99))
                        .build();

        ingredientItems7.forEach(ingredientItem -> ingredientItem.setProduct(beverage2));
        beverage2.setRecipe(ingredientItems7);
        productService.save(beverage2);
    }
    private void buildUsers(){
        AddressEntity address1 = AddressEntity.builder()
                .cityName("Lodz")
                .streetName("Lodowa")
                .streetNumber(6)
                .aptNumber(24)
                .build();

        AddressEntity address2 = AddressEntity.builder()
                .cityName("Warszawa")
                .streetName("Nowy Świat")
                .streetNumber(10)
                .aptNumber(5)
                .build();

        AddressEntity address3 = AddressEntity.builder()
                .cityName("Kraków")
                .streetName("Main Square")
                .streetNumber(1)
                .aptNumber(10)
                .build();

        ContactEntity contact1 = ContactEntity.builder()
                .name("Jan")
                .surname("Kowalski")
                .phoneNumber("123456789")
                .build();

        ContactEntity contact2 = ContactEntity.builder()
                .name("Adam")
                .surname("Nowak")
                .phoneNumber("987654321")
                .build();

        ContactEntity contact3 = ContactEntity.builder()
                .name("Anna")
                .surname("Kowalska")
                .phoneNumber("123123123")
                .build();

        UserEntity user1 = UserEntity.builder()
                .email("user1@gmail.com")
                .password(bCryptHashingUtil.hashPassword("user1"))
                .userRole(UserEntity.UserRole.ROLE_ADMINISTRATOR)
                .build();

        user1.setContact(contact1);
        user1.setAddresses(Collections.singletonList(address1));
        address1.setUser(user1);
        contact1.setUser(user1);
        UserEntity savedUser1 = userService.save(user1);

        UserEntity user2 = UserEntity.builder()
                .email("user2@gmail.com")
                .password(bCryptHashingUtil.hashPassword("user2"))
                .userRole(UserEntity.UserRole.ROLE_EMPLOYEE)
                .build();

        user2.setContact(contact2);
        user2.setAddresses(Collections.singletonList(address2));
        address2.setUser(user2);
        contact2.setUser(user2);
        UserEntity savedUser2 = userService.save(user2);

        UserEntity user3 = UserEntity.builder()
                .email("user3@gmail.com")
                .password(bCryptHashingUtil.hashPassword("user3"))
                .userRole(UserEntity.UserRole.ROLE_CLIENT)
                .build();

        user3.setContact(contact3);
        user3.setAddresses(Collections.singletonList(address3));
        address3.setUser(user3);
        contact3.setUser(user3);
        UserEntity savedUser3 = userService.save(user3);
    }
    private void buildOrders(){
        List<ProductEntity> products = productService.getAll();
        List<UserEntity> users = userService.getAll();

        ProductEntity product1 = products.get(0);
        ProductEntity product2 = products.get(1);
        ProductEntity product3 = products.get(2);
        ProductEntity product4 = products.get(3);

        UserEntity user1 = users.get(0);
        UserEntity user2 = users.get(1);

        OrderItemEntity orderItem1 = OrderItemEntity.builder()
                .product(product3)
                .quantity(2)
                .price(product3.getPrice().multiply(BigDecimal.valueOf(2)))
                .build();

        OrderItemEntity orderItem2 = OrderItemEntity.builder()
                .product(product4)
                .quantity(1)
                .price(product4.getPrice().multiply(BigDecimal.valueOf(1)))
                .build();

        OrderItemEntity orderItem3 = OrderItemEntity.builder()
                .product(product2)
                .quantity(1)
                .price(product2.getPrice().multiply(BigDecimal.valueOf(1)))
                .build();

        OrderItemEntity orderItem4 = OrderItemEntity.builder()
                .product(product1)
                .quantity(4)
                .price(product1.getPrice().multiply(BigDecimal.valueOf(4)))
                .build();

        OrderEntity order1 = OrderEntity.builder()
                .orderDate(LocalDateTime.now())
                .publicId(UUID.randomUUID())
                .totalPrice(orderItem1.getPrice().add(orderItem2.getPrice()))
                .status(OrderEntity.OrderStatus.IN_PROGRESS)
                .build();

        order1.setOrderItems(Arrays.asList(orderItem1, orderItem2));
        order1.setUser(user1);
        order1.setAddress(user1.getAddresses().get(0));

        orderItem1.setOrder(order1);
        orderItem2.setOrder(order1);

        orderService.save(order1);

        OrderEntity order2 = OrderEntity.builder()
                .orderDate(LocalDateTime.now())
                .publicId(UUID.randomUUID())
                .totalPrice(orderItem3.getPrice().add(orderItem4.getPrice()))
                .status(OrderEntity.OrderStatus.IN_PROGRESS)
                .build();

        order2.setOrderItems(Arrays.asList(orderItem3, orderItem4));
        order2.setUser(user2);
        order2.setAddress(user2.getAddresses().get(0));

        orderItem3.setOrder(order2);
        orderItem4.setOrder(order2);

        orderService.save(order2);
    }

    private void buildReservations(){
        reservationTableService.save(ReservationTableEntity.builder()
                .number(1)
                .seats(2)
                .build());

        reservationTableService.save(ReservationTableEntity.builder()
                .number(2)
                .seats(4)
                .build());

        reservationTableService.save(ReservationTableEntity.builder()
                .number(3)
                .seats(6)
                .build());

        reservationTableService.save(ReservationTableEntity.builder()
                .number(4)
                .seats(8)
                .build());

        reservationTableService.save(ReservationTableEntity.builder()
                .number(5)
                .seats(2)
                .build());

        reservationTableService.save(ReservationTableEntity.builder()
                .number(6)
                .seats(4)
                .build());

        reservationTableService.save(ReservationTableEntity.builder()
                .number(7)
                .seats(6)
                .build());

        reservationTableService.save(ReservationTableEntity.builder()
                .number(8)
                .seats(12)
                .build());
    }

    private void buildAll(){
        buildProducts();
        buildUsers();
        buildOrders();
        buildReservations();
    }

    @Override
    public void run(String... args) {
        buildAll();
    }
}
