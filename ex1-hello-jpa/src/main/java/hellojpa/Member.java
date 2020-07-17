package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "Member") //이런 경우는 생략 가능
public class Member {

    @Id
    private Long id;

    //@Column(name = "name") //이렇게 같은 경우엔 생략해도된다. 서로 매핑정보가 다를때 기입하자.
    private String name;


    public Member(){
        //JPA는 기본생성자가 하나 있어야한다.
    }
    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
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
