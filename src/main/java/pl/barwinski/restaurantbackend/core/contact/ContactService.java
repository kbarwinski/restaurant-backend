package pl.barwinski.restaurantbackend.core.contact;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;
    public ContactEntity save(ContactEntity address) {
        return contactRepository.save(address);
    }
}
