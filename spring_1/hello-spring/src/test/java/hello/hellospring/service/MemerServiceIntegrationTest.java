package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // test가 끝나면 rollback 해준다. 그래서 db에 넣었던걸 지우고 다시 실행.
class MemberServiceIntegrationTest {

    //MemberService memberService = new MemberService();
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    // MemberService에서 만든 MemoryMemberRepository와 여기서의 MemoryMemberRepository가 다른 객체라 문제가 생길 수 있다.
    // 같은 객체로만 테스트 하는게 옳다.

    // 스프링 컨테이너한테 객체를 달라고 해야 함.
    // 테스트 코드는 가장 편하게 코딩하면 되므로 annotation을 바로 쓴다.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring4");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring4");

        Member member2 = new Member();
        member2.setName("spring4");

        //when
        memberService.join(member1);
        /*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e){
            // 같은 회원이 등록되었기 때문에 예외가 발생하고, 그 때 출력되는 msg와 다른 msg를 넣은 상태.
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하지 않는 회원입니다.");
        }
        */
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then
    }
}