package pl.barwinski.restaurantbackend.core.contact;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.barwinski.restaurantbackend.core.contact.ContactEntity;
import pl.barwinski.restaurantbackend.core.user.UserEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends CrudRepository<ContactEntity, Long>{

}
