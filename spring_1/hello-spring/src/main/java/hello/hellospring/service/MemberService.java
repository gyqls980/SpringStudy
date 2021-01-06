package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.midi.SysexMessage;
import java.util.List;
import java.util.Optional;

// JPA를 쓰려면 Service에 항상 @Transactional이 있어야 한다.
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long join(Member member){

        long start = System.currentTimeMillis();
        try{
            // 중복 이름의 회원이 있으면 x
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
        }finally {
            {
                long finish = System.currentTimeMillis();
                long timeMs = finish - start;
                System.out.println("join = " + timeMs + "ms");
            }
        }
    }

    private void validateDuplicateMember(Member member){
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // Optional의 메소드를 활용할 수 있다.(ifPresent)
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });

        // result.get()으로 활용할 수 있지만 직접적으로 꺼내서 확인하는건 권장 X
    }

    // 전체 회원 조회
    public List<Member> findMembers(){
        long start = System.currentTimeMillis();
        try{
            return memberRepository.findAll();
        }finally {
            {
                long finish = System.currentTimeMillis();
                long timeMs = finish - start;
                System.out.println("findMembers = " + timeMs + "ms");
            }
        }

    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
