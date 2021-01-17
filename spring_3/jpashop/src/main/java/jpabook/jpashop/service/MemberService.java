package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 효율성 증가. 읽기에는 가급적 사용해라
@RequiredArgsConstructor //lombok -> final인 필드만 생성자를 만들어줌
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 가입
    @Transactional //이거는 쓰기도 해야하므로 따로 작성
    public Long join(Member member){
        //중복회원불가
        validateDuplicateMember(member);
        memberRepository.save(member);
        // .persist()하면 PK와 함께 생성되서(generateValue) getId()가 가능
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION

        //해당 메서드를 동시 접속하게 되면 문제가 생길 수 있다. 실무에서는 한번 더 방어를 해야함.
        //db에서 제약조건을 unique로로
       List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 굳이 service를 거치는 이유? 바로 repository 함수를 사용해도 될 것 같은데

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


}
