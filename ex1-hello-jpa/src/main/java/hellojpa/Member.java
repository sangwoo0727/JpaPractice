package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//@Table(name = "Member") //이런 경우는 생략 가능
public class Member {

    @Id
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    //EnumType.ORDINAL로 하게되면 enum의 순서를 데이터베이스에 저장하게 되고, 사용하지 않는다.
    //순서를 디비에 넣을때 원래 USER,ADMIN이 enum타입에 있었을 때,
    //첫번째 데이터는 RoleType이 0, 두번째 데이터는 1로 들어간다고 해보자. 즉 첫번째 데이터는 USER, 두번째 데이터는 ADMIN
    //근데 여기서 enum 타입이 GUEST,USER,ADMIN으로 바뀌고,
    //세번째 데이터는 GUEST라 0이 들어갈때, 디비값이 서로 다 본래 취지랑 맞지 않게된다.

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    //@Temporal은 자바8부터 안써도된다.
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Lob
    private String description;

    @Transient
    private int temp; //데이터베이스 테이블에 매핑시키지 않는다. @Transient는
    
    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}
