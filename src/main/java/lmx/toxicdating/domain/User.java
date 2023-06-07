package lmx.toxicdating.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usr")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;

    private String hashPassword;

    private String email;

    private String gender;

    private Boolean active;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dateOfBirth;

    private String bio;

    @ElementCollection
    private List<String> pictures=new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "usr_chats",
            joinColumns = @JoinColumn(name="usr_id"),
            inverseJoinColumns = @JoinColumn(name="chat_id")
    )
    private List<Chat> chats=new ArrayList<>();

    @OneToMany(mappedBy = "source",cascade=CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "sender",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();
}
