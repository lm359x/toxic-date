package lmx.toxicdating.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "lik")
@Getter
@Setter
public class Like {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;

    private UUID target;

    @ManyToOne
    @JoinColumn(name = "usr_id", nullable = false)
    private User source;

    public Like(LocalDateTime localDateTime, UUID target, User source) {
        this.localDateTime = localDateTime;
        this.target = target;
        this.source = source;
    }
}
