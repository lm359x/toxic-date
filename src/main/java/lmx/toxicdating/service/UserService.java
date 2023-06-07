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

    public User createUser(User user) {
        user.setHashPassword(
                user.getHashPassword() //replace with HASH later
        );
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User getUser(UUID id){
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(User user, UUID uuid) {
        User userFromDb = userRepository.findById(uuid).orElse(null);
        if (userFromDb != null) {
            BeanUtils.copyProperties(user,userFromDb,"id","creationDate");
            userRepository.save(userFromDb);
        }
        return userFromDb;
    }

    public void deleteMessage(UUID uuid) {
        userRepository.findById(uuid).ifPresent(userRepository::delete);
    }
}
