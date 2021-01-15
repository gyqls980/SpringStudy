package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    // starter-jpa 를 보고 스프링부트가 em을 알아서 만들어줌
    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId(); // commend, query 분리(저장 후 id정도만 반환)
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }
}
