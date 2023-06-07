package lmx.toxicdating.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {
    private String hashPassword;
    private String picture;
    private Boolean active;
    private String bio;
}
