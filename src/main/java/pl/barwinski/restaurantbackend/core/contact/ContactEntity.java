package pl.barwinski.restaurantbackend.core.contact;

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
@Table(name = "contacts")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContactEntity {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotNull
    @Length(max = 255)
    private String name;

    @NotBlank
    @NotNull
    @Length(max = 255)
    private String surname;

    @NotBlank
    @NotNull
    @Length(max = 20)
    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}