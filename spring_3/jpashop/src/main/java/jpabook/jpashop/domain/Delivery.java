package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    // 1:1일 때는 접근이 많은 곳에 FK를 준다.(orders > delivery)
    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // ORDINAL 절대 X(순서에 따라)
    private DeliveryStatus status; //READY, COMP(배송)
}
