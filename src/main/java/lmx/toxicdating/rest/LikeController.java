package lmx.toxicdating.rest;

import lmx.toxicdating.domain.Like;
import lmx.toxicdating.domain.User;
import lmx.toxicdating.domain.dto.CreateLikeDto;
import lmx.toxicdating.exception.EntityNotFoundException;
import lmx.toxicdating.service.LikeService;
import lmx.toxicdating.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private final LikeService likeService;
    private final UserService userService;

    public LikeController(LikeService likeService, UserService userService) {
        this.likeService = likeService;
        this.userService = userService;
    }

    @PostMapping
    public Like create(@RequestBody CreateLikeDto createLikeDto){
        User sourceUser = userService.getUserById(createLikeDto.getSource());
        User targetUser = userService.getUserById(createLikeDto.getTarget());
        if(sourceUser==null){
            throw new EntityNotFoundException("Not found user with id "+createLikeDto.getSource());
        }
        if(targetUser==null){
            throw new EntityNotFoundException("Not found user with id "+createLikeDto.getTarget());
        }
        return likeService.create(createLikeDto.getSource(),createLikeDto.getTarget());
    }
}
