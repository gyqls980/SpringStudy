package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter //@Table왜 안해줌?
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded // 내장 타입 class Address에는 @Embeddable
    private Address address;

    // orders와의 양방향 참조에서 읽기 전용 -> orders가 주인(FK가 있는 쪽)
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();


}
