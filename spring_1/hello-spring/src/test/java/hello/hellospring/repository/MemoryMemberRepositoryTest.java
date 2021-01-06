package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Optional;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 한 메소드가 끝날 때 마다 실행되는 것.
    @AfterEach
    public void afterEach() {
        // MemoryMemberRepository에 메소드 추가함.
        repository.clearStore();
    }

    // 이걸 하면 구현했던 save() 함수가 실행된다.
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        // System.out.println("result = " + (result == member));

        // 기댓값, 실행값. 다르면 실행 시 오류가 난다.
        //Assertions.assertEquals(member, null);

        // 위에 것은 기댓값, 실행값 순서가 헷갈리기 쉬워서 이게 더 많이 활용한다.
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // shift+F6 하면 member2로 한번에 바꿀 수 있다.
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // shift+F6 하면 member2로 한번에 바꿀 수 있다.
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);

    }

}
