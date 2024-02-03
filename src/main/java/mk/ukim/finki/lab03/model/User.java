package mk.ukim.finki.lab03.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@Entity
@Table(name = "movie_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Convert(converter = UserFullnameConverter.class)
    private UserFullname userFullname;
    private String password;
    @DateTimeFormat
    private LocalDate dateOfBirth;
    @OneToMany(mappedBy = "user")
    private List<ShoppingCart> carts;

    public User(String username, String password, String name, String surname, LocalDate dateOfBirth) {
        this.username = username;
        UserFullname userFullname=new UserFullname();
        userFullname.setName(name);
        userFullname.setSurname(surname);
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public User() {

    }
}
