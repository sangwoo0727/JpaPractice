package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Identity 전략은 id에 내가 직접 값을 넣는 것이 아니다.
    //null로 날아가면 db에서 그때 값을 세팅한다.
    //id 값을 알 수 있는 시점은 디비에 값이 들어가고 난 후이다.
    //근데 영속성 컨텍스트에서 관리되려면 무조건 pk 값이 있어야 한다.
    // 영속 상태가 되면 1차 캐시에 id도 세팅이 되어있어야 하는데, 커밋되기 전이므로, jpa에서 어떻게 해야할지 모름
    // 그래서 Identity 전략에서만 예외적으로 em.persist 호출하자마자 db에 insert 쿼리를 날린다.

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
