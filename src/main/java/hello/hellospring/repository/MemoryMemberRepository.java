package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // sequence는 0, 1, 2 .. key값을 생성해줌.

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null이 반환될 가능성이 있으므로 Optional로 감싸준다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // loop를 돌린다.
                .filter(member -> member.getName().equals(name)) //파라미터로 넘어온 name과 값이 같은지 확인
                .findAny(); // 찾으면 반환하고 못찾으면 Optional이 null을 포함해서 반환한다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store에 있는 멤버(value)들이 반환된다.
    }

    public void clearStore(){
        store.clear(); // store를 싹 다 비운다.
    }
}
