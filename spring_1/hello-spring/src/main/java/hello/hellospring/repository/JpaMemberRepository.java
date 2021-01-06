package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // JPA 모든 것은 EntityManager로 동작한다.
    // build.gradle에 추가한 것이 알아서 EntityManager이라는 걸 만들어줌.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // persistent : 영속하다.
        // 이렇게만 하면 모든 걸 다 해준다. insert 쿼리, setId 등등
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // pk 기반일 때 = .find(조회 타입, pk)
       Member member = em.find(Member.class, id);
       return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // name은 pk가 아니므로 객체지향 쿼리 언어를 써야 한다.
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // JPQL : 객체(테이블X)를 대상으로 쿼리를 날리는 것.
        // select m = Member(m)객체 자체를 select
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
