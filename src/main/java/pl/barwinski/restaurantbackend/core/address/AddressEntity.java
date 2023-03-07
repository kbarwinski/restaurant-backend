package pl.barwinski.restaurantbackend.core.address;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressEntity {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotNull
    private int apt_number;

    @NotBlank
    @NotNull
    @Length(max = 255)
    private String street_name;

    @NotBlank
    @NotNull
    private int street_number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user.id")
    private UserEntity user;
}