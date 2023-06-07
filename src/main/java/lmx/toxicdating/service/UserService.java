package lmx.toxicdating.service;

import lmx.toxicdating.domain.User;
import lmx.toxicdating.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //TODO: replace with SS PaswordEncoder
    public User createUser(User user) {
        user.setHashPassword(
                user.getHashPassword()
                // Hashing.sha256().hashString(user.getHashPassword(), StandardCharsets.UTF_8).toString()
        );
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User getUserById(UUID id){
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email){return userRepository.findByEmail(email);}

    public List<User> getActiveUsers(){return userRepository.findAllByActive(true);}

    public User updateUser(User user) {
        User userFromDb = userRepository.findById(user.getId()).orElse(null);
        if (userFromDb != null) {
            BeanUtils.copyProperties(user,userFromDb,"id");
            userRepository.save(userFromDb);
        }
        return userFromDb;
    }

    public void deleteUser(UUID uuid) {
        userRepository.findById(uuid).ifPresent(userRepository::delete);
    }
}
