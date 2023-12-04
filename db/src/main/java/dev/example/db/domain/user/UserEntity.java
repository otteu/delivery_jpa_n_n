package dev.example.db.domain.user;

import dev.example.db.domain.BaseEntity;
import dev.example.db.domain.user.enums.UserStatus;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
@Builder
//@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(nullable = false, length = 150)
    private String address;

    @ColumnDefault("null")
    @Column()
    private LocalDateTime registeredAt;

    @ColumnDefault("null")
    @Column()
    private LocalDateTime unregisteredAt;

    @ColumnDefault("null")
    @Column()
    private LocalDateTime lastLoginAt;


}
