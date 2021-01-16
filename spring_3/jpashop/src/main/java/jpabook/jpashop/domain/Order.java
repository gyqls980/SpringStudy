package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    // member와의 양방향 참조에서 연관관계 주인(FK가 있는 쪽)
    @ManyToOne
    @JoinColumn(name = "member_id") //FK
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    // 1:1일 때는 접근이 많은 곳에 FK를 준다.(orders > delivery)
    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    // Date 경우 @Date 필요했는데, JAVA8부터는 LocalDateTime를 사용 시 hivernate가 알아서 해준다.
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문 상태 [ORDER, CANCEL]
}
