package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

// 스프링 빈으로 자동 등록(컴포넌트 스캔)
@Repository
@RequiredArgsConstructor //lombok -> final인 필드만 생성자를 만들어줌
public class MemberRepository {

    // em은 원래 @PersistenceContext로 생성자 없이도 사용할 수 있게 되어있음.
    // 스프링에서는 @Autowired도 가능하게 해줌
    // -> final 붙이고 @RequiredArgsConstructor도 가능
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        // JPQL 은 entity객체를 대상으로 수행. SQL은 테이블을 대상으로 수행.
        // .createQuery(JQPL query, 반환 타입)
        return em.createQuery("select  m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
