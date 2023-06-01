package pl.barwinski.restaurantbackend.core.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
    UserEntity save(UserEntity user);
    Optional<UserEntity> findById(Long id);

    Page<UserEntity> findAll(Pageable pageable);

    List<UserEntity> findAll();
    Optional<UserEntity> findByEmailEquals(String email);
}
