package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //MemberService memberService = new MemberService();
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    // MemberService에서 만든 MemoryMemberRepository와 여기서의 MemoryMemberRepository가 다른 객체라 문제가 생길 수 있다.
    // 같은 객체로만 테스트 하는게 옳다.

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // 동작하기 전
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        // 같은 memberRepository를 사용하게 됨. = DI (Dependency Injection)
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }


    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

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
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

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

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}