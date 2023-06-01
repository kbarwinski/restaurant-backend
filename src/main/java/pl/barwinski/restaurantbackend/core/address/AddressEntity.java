package pl.barwinski.restaurantbackend.core.address;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import pl.barwinski.restaurantbackend.core.user.UserEntity;

@Getter
@Setter
@Entity
@Builder
@Table(name = "addresses")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class AddressEntity {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private int aptNumber;

    @NotBlank
    @NotNull
    @Length(max = 255)
    private String streetName;

    @NotBlank
    @NotNull
    @Length(max = 255)
    private String cityName;

    @NotNull
    private int streetNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}