package lmx.toxicdating.rest;


import lmx.toxicdating.domain.User;
import lmx.toxicdating.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//TODO: add geolocation impl
@RestController
@RequestMapping("/v1/search")
@CrossOrigin
public class SearchController {
    private final UserService userService;

    public SearchController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public List<User> search(){
        return userService.getActiveUsers();
    }
}
