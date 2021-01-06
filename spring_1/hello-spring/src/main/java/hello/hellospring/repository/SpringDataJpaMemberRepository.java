package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 인터페이스가 인터페이스를 받을 때는 implements 가 아닌 extends 사용
// 인터페이스는 다중 상속이 된다.
// JpaRepository : jpa.repository 에서 제공하는 인터페이스. 자동으로 구현체를 만들고 스프링 빈에 등록해준다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
