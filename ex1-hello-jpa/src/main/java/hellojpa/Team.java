package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team") //1대다 매핑에서 나의 반대편에는 team이 있다라는 표시
    //mappedBy는 엄청 어려운 난이도.
    // 딜레마가 생기는게, Member의 team값을 바꿨을 때 TEAM_ID가 업데이트 되어야 할까, 아니면
    // Team의 members를 업데이트 했을 때, TEAM_ID가 업데이트 되어야 할까..
    // 객체의 두 관계 중 하나를 연관관계의 주인으로 정해야되고,
    // 주인이 아닌 쪽은 mappedBy 속성으로 주인을 지정한다.
    private List<Member> members = new ArrayList<>(); //ArrayList로 초기화하는 것이 관례이다.

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

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
}
