package pl.barwinski.restaurantbackend.core.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.barwinski.restaurantbackend.core.address.AddressEntity;
import pl.barwinski.restaurantbackend.core.user.exception.UserNotFoundException;
import pl.barwinski.restaurantbackend.core.user.exception.WrongPasswordException;
import pl.barwinski.restaurantbackend.security.auth.JwtTokenProvider;
import pl.barwinski.restaurantbackend.security.hashing.IHashingUtil;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final IHashingUtil hashingUtil;
    private final JwtTokenProvider jwtTokenProvider;

    public UserEntity save(UserEntity user){
        user.setPassword(hashingUtil.hashPassword(user.getPassword()));
        return userRepository.save(user);
    }

    public LoginModel login(String email, String password){
        Optional<UserEntity> user = userRepository.findByEmailEquals(email);
        if(user.isPresent()){
            if(hashingUtil.verifyPassword(password, user.get().getPassword())){
                return new LoginModel(
                        user.get().getEmail(),
                        user.get().getUserRole().toString(),
                        jwtTokenProvider.generateToken(user.get()));
            }
            else
                throw new WrongPasswordException();
        }
        throw new UserNotFoundException();
    }

    public UserEntity register(UserEntity user){
        Optional<UserEntity> duplicate = userRepository.findByEmailEquals(user.getEmail());
        if(duplicate.isPresent())
            throw new RuntimeException("User already exists");

        return userRepository.save(user);
    }
    public List<UserEntity> getAll(){
        return userRepository.findAll();
    }
    public Page<UserEntity> getAll(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public UserEntity getById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    public UserEntity getByEmail(String email) {
        return userRepository.findByEmailEquals(email)
                .orElseThrow(UserNotFoundException::new);
    }
}
