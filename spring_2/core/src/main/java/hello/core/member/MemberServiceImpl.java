package hello.core.member;

// 인터페이스 구현체가 하나 있을 때 이런 식으로 구현체명을 생성
public class MemberServiceImpl implements MemberService {

    // DIP 위배되므로 생성자 주입 방법으로 활용한다.
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
