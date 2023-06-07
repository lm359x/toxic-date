package lmx.toxicdating.service;

import lmx.toxicdating.domain.Chat;
import lmx.toxicdating.domain.Like;
import lmx.toxicdating.domain.User;
import lmx.toxicdating.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserService userService;
    private final ChatService chatService;

    @Autowired
    public LikeService(LikeRepository likeRepository, UserService userService, ChatService chatService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.chatService = chatService;
    }

    public Like create(UUID source, UUID target) {
        User sourceUser = userService.getUserById(source);
        User targetUser = userService.getUserById(target);
        if (sourceUser != null && targetUser != null) {
            Like like = new Like(LocalDateTime.now(), target, sourceUser);
            likeRepository.save(like);
            sourceUser.getLikes().add(like);
            userService.updateUser(sourceUser);
            Like first = targetUser.getLikes().stream().filter(l ->
                    l.getTarget().equals(source)
            ).findFirst().orElse(null);
            if(first!=null){
                Chat newChat = new Chat();
                newChat.getUsers().add(sourceUser);
                newChat.getUsers().add(targetUser);
                chatService.createChat(newChat);
            }
            return like;
        }

        return null;
    }


}
