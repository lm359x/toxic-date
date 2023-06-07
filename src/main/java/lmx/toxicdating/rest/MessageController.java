package lmx.toxicdating.rest;

import lmx.toxicdating.domain.Chat;
import lmx.toxicdating.domain.Message;
import lmx.toxicdating.domain.User;
import lmx.toxicdating.domain.dto.CreateMessageDto;
import lmx.toxicdating.domain.dto.UpdateMessageDto;
import lmx.toxicdating.exception.EntityNotCreatedException;
import lmx.toxicdating.exception.EntityNotFoundException;
import lmx.toxicdating.exception.UserNotInChatException;
import lmx.toxicdating.service.ChatService;
import lmx.toxicdating.service.MessageService;
import lmx.toxicdating.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private  final MessageService messageService;
    private final ChatService chatService;
    private final UserService userService;

    public MessageController(MessageService messageService, ChatService chatService, UserService userService) {
        this.messageService = messageService;
        this.chatService = chatService;
        this.userService = userService;
    }

    @GetMapping
    public List<Message> getAll(){
        return messageService.getAllMessages();
    }

    @GetMapping("/filter/{chatId}")
    public List<Message> getAllByChat(@PathVariable UUID chatId){
        Chat chat = chatService.getChat(chatId);
        if(chatId ==null){
            throw new EntityNotFoundException("No chat found with id "+chat);
        }
        return messageService.getChatsMessages(chat);
    }
    @GetMapping("/{id}")
    public Message getOne(@PathVariable UUID id){
        Message message = messageService.getMessage(id);
        if(message==null){
            throw new EntityNotFoundException("Not found message with id "+id);
        }
        return message;
    }

    @PostMapping()
    public Message create(@RequestBody CreateMessageDto createMessageDto){
        Message message = new Message();
        User sender = userService.getUserById(createMessageDto.getSenderId());
        if(sender==null){
            throw new EntityNotFoundException("Not found user with id "+createMessageDto.getSenderId());
        }
        Chat chat = chatService.getChat(createMessageDto.getChatId());
        if(chat==null){
            throw new EntityNotFoundException("Not found chat with id "+ createMessageDto.getChatId());
        }
        if(!chat.getUsers().contains(sender)){
            throw new UserNotInChatException("User "+ sender.getId()+" is not in chat "+chat.getId());
        }
        message.setCreationDate(LocalDateTime.now());
        message.setSender(sender);
        message.setChat(chat);
        message.setText(createMessageDto.getText());
        Message createdMessage = messageService.createMessage(message);
        if(createdMessage==null){
            throw new EntityNotCreatedException("Can not create message");
        }
        return createdMessage;
    }

    @PutMapping("/{id}")
    public Message update(@PathVariable UUID id, @RequestBody UpdateMessageDto updateMessageDto){
        Message message = messageService.getMessage(id);
        if(message==null){
            throw new EntityNotFoundException("Not found message with id "+id);
        }
        if(updateMessageDto.getText()!=null){
            message.setText(updateMessageDto.getText());
        }
        return messageService.updateMessage(message);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        messageService.deleteMessage(id);
    }

}
