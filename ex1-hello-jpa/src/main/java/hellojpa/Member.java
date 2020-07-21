package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//@Table(name = "Member") //이런 경우는 생략 가능
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    //기본키를 직접 할당할 때는 @Id만 사용
    // @GeneratedValue는 autoIncrement처럼 데이터베이스가 기본키를 생성하도록 위임하는 것.

    @Column(name = "name", nullable = false)
    private String username;

    public Member() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
