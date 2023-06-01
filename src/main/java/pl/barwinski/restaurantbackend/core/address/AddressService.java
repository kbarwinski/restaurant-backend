package pl.barwinski.restaurantbackend.core.address;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    public AddressEntity save(AddressEntity address) {
        return addressRepository.save(address);
    }

    public AddressEntity getById(long addressId) {
        return addressRepository.findById(addressId).orElseThrow();
    }
}
