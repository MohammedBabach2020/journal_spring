package ma.micda.journal.models;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@Table(name = "journal")
public class Journal implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "title")
        @Size(max = 15)
        private String title;

        @Column(name = "text")
        @Size(max = 500)
        private String text;

        @Column(name = "userId")
        private Long userId;

        @CreatedDate
        @Column(name = "created_at", nullable = false, updatable = false)
        private LocalDateTime createdAt = LocalDateTime.now();

}
