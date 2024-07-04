package ma.micda.journal.models;

import lombok.*;
import ma.micda.journal.enumeration.ERole;

import javax.persistence.*;

@Entity
@Setter
@Getter
@EqualsAndHashCode
@Table(name = "roles")
public class Role {

    public Role() {
    }

    public Role(ERole name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 40)
    private ERole name;
}
