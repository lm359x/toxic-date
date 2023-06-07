package lmx.toxicdating.service;

import lmx.toxicdating.domain.Chat;
import lmx.toxicdating.domain.User;
import lmx.toxicdating.repository.ChatRepository;
import lmx.toxicdating.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public ChatService(ChatRepository chatRepository, UserRepository userRepository, UserService userService) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public Chat createChat(Chat chat) {
        Chat savedChat =  chatRepository.save(chat);
        for(User u: savedChat.getUsers()){
            u.getChats().add(savedChat);
            userService.updateUser(u);
        }
        return savedChat;
    }

    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    public List<Chat> getUsersChats(User user) {
        return chatRepository.findAllByUsersContaining(user);
    }

    public Chat updateChat(Chat chat, UUID uuid) {
        Chat chatFromDb = chatRepository.findById(uuid).orElse(null);
        if (chatFromDb != null) {
            BeanUtils.copyProperties(chat,chatFromDb,"id","users");
            chatRepository.save(chatFromDb);
        }
        return chatFromDb;
    }

    public Chat getChat(UUID id){
        return chatRepository.findById(id).orElse(null);
    }

    //returns true if there is no active chat between users, so we can create chat
    public boolean checkChat(List<User> users){
        User userFromDb1 = userRepository.findById(users.get(0).getId()).orElseThrow();
        User userFromDb2 = userRepository.findById(users.get(1).getId()).orElseThrow();
        if(getUsersChats(userFromDb1).stream().anyMatch(c -> c.getUsers().contains(userFromDb2)))
            return false;
        return getUsersChats(userFromDb2).stream().noneMatch(c -> c.getUsers().contains(userFromDb1));
    }

    public void deleteChat(UUID uuid) {
        chatRepository.findById(uuid).ifPresent(chatRepository::delete);
    }
}
