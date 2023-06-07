package lmx.toxicdating.rest;

import lmx.toxicdating.domain.Chat;
import lmx.toxicdating.domain.User;
import lmx.toxicdating.domain.dto.UpdateChatDto;
import lmx.toxicdating.domain.dto.CreateChatDto;
import lmx.toxicdating.exception.EntityNotCreatedException;
import lmx.toxicdating.exception.EntityNotFoundException;
import lmx.toxicdating.service.ChatService;
import lmx.toxicdating.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/chats")
public class ChatController {
    private final ChatService chatService;
    private  final UserService userService;

    public ChatController(ChatService chatService, UserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }

    @GetMapping
    public List<Chat> getAll(){
        return chatService.getAllChats();
    }

    @GetMapping("/{id}")
    public Chat getOne(@PathVariable UUID id){
        Chat chat = chatService.getChat(id);
        if(chat==null){
            throw new EntityNotFoundException("Not found chat with id "+id);
        }
        return chat;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Chat create(@RequestBody CreateChatDto createChatDto){
        List<User> users = new ArrayList<>();
        users.add(userService.getUserById(createChatDto.getFirstUserId()));
        users.add(userService.getUserById(createChatDto.getSecondUserId()));
        if(users.contains(null)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"null users");
        }

        if(!chatService.checkChat(users)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"users have active chat");
        }
        Chat chat = new Chat();
        chat.setUsers(users);
        Chat createdChat = chatService.createChat(chat);
        if(createdChat==null){
            throw new EntityNotCreatedException("Can not create chat");
        }
        return createdChat;
    }
    @PutMapping("/{id}")
    public Chat update(@RequestBody UpdateChatDto updateChatDto, @PathVariable UUID id){
        Chat chatFromDb = chatService.getChat(id);
        if(chatFromDb==null){
            throw new EntityNotFoundException("Not found chat with id "+id);
        }
        chatFromDb.getMessages().addAll(updateChatDto.getMessageList());
        return chatService.updateChat(chatFromDb,id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        chatService.deleteChat(id);
    }
}
