package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // final은 무조건 값이 할당되어야 하므로 여기서 지워준다
    //private DiscountPolicy discountPolicy;
    // 하지만 DIP 만족을 위해 AppConfig를 활용하며 생성자 주입 방법을 사용.

    // 이제 OrderServiceImpl에는 memoryRepository에 대한 정보만 사용한다.
    // 추상화에만 의존하는 것. DIP를 지키는 것.
    // 생성자 주입 방법을 활용 - AppConfig에서 구현체를 선택해줌.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    // 철저하게 DIP를 지키고 있다. 어떤 구현체가 들어올지 전혀 모르고 인터페이스에만 의존하고 있음.
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
