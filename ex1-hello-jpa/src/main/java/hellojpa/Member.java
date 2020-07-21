package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@TableGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        table = "MY_SEQUENCES", //테이블 이름
        pkColumnName = "MEMBER_SEQ", allocationSize = 1) //PK 컬럼명
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
                    generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    //기본키를 직접 할당할 때는 @Id만 사용
    // @GeneratedValue는 autoIncrement처럼 데이터베이스가 기본키를 생성하도록 위임하는 것.

    @Column(name = "name", nullable = false)
    private String username;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Member() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
