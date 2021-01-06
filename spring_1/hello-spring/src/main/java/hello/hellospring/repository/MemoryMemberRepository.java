package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // sequence : key 값을 만들어주는 것?

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // Optional.ofNullable() : null일 때 이렇게 반환하면 클라이언트에서 다른 작업을 할 수 있다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // lambda를 활용함.
        // stream() :
        // 파라미터로 넘어온 name과 member의 name이 같은지 필터링한다.
        // 찾으면 반환하는 것.
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // Map이었지만 반환은 List이다.
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
