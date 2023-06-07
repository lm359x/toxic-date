package lmx.toxicdating.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMessageDto {
    //TODO: Check if we can use Entity types here
    private UUID chatId;
    private UUID senderId;
    private String text;
}
