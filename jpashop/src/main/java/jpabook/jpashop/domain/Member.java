package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "member")
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "zipcode")
    private String zipcode;

    //양방향 관계가 필요해져서, orders가 필요해졌다 할때,,
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
