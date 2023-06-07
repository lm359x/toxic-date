package lmx.toxicdating.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "usr")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;

    private String hashPassword;

    private String email;

    @ElementCollection
    private List<String> pictures=new ArrayList<>();

    @ManyToMany
    private List<Chat> chats=new ArrayList<>();

    @OneToMany(mappedBy = "source")
    private Set<Like> likes;
}
