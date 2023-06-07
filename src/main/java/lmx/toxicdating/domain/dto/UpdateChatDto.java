package lmx.toxicdating.domain.dto;

import lmx.toxicdating.domain.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateChatDto {
    List<Message> messageList;
}
