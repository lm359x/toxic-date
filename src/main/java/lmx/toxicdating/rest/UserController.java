package lmx.toxicdating.rest;

import lmx.toxicdating.domain.User;
import lmx.toxicdating.domain.dto.CreateUserDto;
import lmx.toxicdating.domain.dto.UpdateUserDto;
import lmx.toxicdating.exception.EntityNotFoundException;
import lmx.toxicdating.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/users")
@CrossOrigin
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getOne(@PathVariable UUID id){
        User user = userService.getUserById(id);
        if(user==null){
            throw new EntityNotFoundException("Not found user with id "+id);
        }
        return user;
    }

    //TODO: validation
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User create(@RequestBody CreateUserDto createUserDto){
        User user = new User();
        user.setName(createUserDto.getName());
        user.setHashPassword(createUserDto.getHashPassword());
        user.setEmail(createUserDto.getEmail());
        user.setGender(createUserDto.getGender());
        user.setDateOfBirth(LocalDateTime.parse(createUserDto.getDateOfBirth(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        user.setPictures(createUserDto.getPictureLinks());
        user.setActive(true);
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable UUID id, @RequestBody UpdateUserDto updateUserDto){
        User userFromDb = userService.getUserById(id);
        if(userFromDb==null){
            throw new EntityNotFoundException("Not found user with id "+id);
        }
        if(updateUserDto.getHashPassword()!=null){
            userFromDb.setHashPassword(updateUserDto.getHashPassword());
        }
        if(updateUserDto.getPicture()!=null){
            userFromDb.getPictures().add(updateUserDto.getPicture());
        }
        if(updateUserDto.getBio()!=null){
            userFromDb.setBio(updateUserDto.getBio());
        }
        if(updateUserDto.getActive()!=null) {
            userFromDb.setActive(updateUserDto.getActive());
        }
        return userService.updateUser(userFromDb);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        userService.deleteUser(id);
    }
}
