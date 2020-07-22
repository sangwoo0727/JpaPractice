package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Member {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

//    @Column(name = "TEAM_ID") //db에 맞춰서 외래 키를 그대로 사용
//    private Long teamId;

    @ManyToOne //Member와 Team의 관계는 N:1이므로, 이렇게 어노테이션을 붙혀야 한다.
    @JoinColumn(name = "TEAM_ID") //team 레퍼런스와 Member테이블에 있는 TEAM_ID와 매핑해야 한다.
    private Team team; //이렇게만 쓰면 에러가 난다. -> JPA에게 이 둘의 관계가 무슨관계인지 알려주어야 한다. ->어노테이션 붙혀야함.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
