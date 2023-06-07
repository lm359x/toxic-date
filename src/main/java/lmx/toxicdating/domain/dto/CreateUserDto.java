package lmx.toxicdating.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    private String name;

    private String hashPassword;

    private String email;

    private String gender;

    private String dateOfBirth;
    private List<String> pictureLinks;
}
