package lmx.toxicdating.service;

import lmx.toxicdating.domain.Chat;
import lmx.toxicdating.domain.Message;
import lmx.toxicdating.repository.MessageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public List<Message> getChatsMessages(Chat chat){
        return messageRepository.findAllByChat(chat).stream()
                .sorted(Comparator.comparing(Message::getCreationDate)).toList();
    }

    public Message getMessage(UUID id){
        return messageRepository.findById(id).orElse(null);
    }

    public Message updateMessage(Message message) {
        Message messageFromDb = messageRepository.findById(message.getId()).orElse(null);
        if (messageFromDb != null) {
            BeanUtils.copyProperties(message,messageFromDb,"id","creationDate");
            messageRepository.save(messageFromDb);
        }
        return messageFromDb;
    }

    public void deleteMessage(UUID uuid) {
        messageRepository.findById(uuid).ifPresent(messageRepository::delete);
    }
}
