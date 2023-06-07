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

    public ChatService(ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    public Chat createChat(Chat chat) {
        for(User u: chat.getUsers()){
            //needed???
            u.getChats().add(chat);
            userRepository.save(u);
        }
        return chatRepository.save(chat);
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
            BeanUtils.copyProperties(chat,chatFromDb,"id","chats","likes");
            chatRepository.save(chatFromDb);
        }
        return chatFromDb;
    }

    public void deleteChat(UUID uuid) {
        chatRepository.findById(uuid).ifPresent(chatRepository::delete);
    }
}
