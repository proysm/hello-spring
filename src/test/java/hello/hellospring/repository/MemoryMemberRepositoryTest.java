package hello.hellospring.repository;


import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 하나의 메소드 실행이 끝날 때마다 동작한다. 이게 없으면 오류남.
    public void afterEach(){
        repository.clearStore();
    }

    @Test // test를 위한 어노테이션을 붙이면 이제 실행할 수 있다.
    public void save(){
        Member member = new Member();
        member.setName("sping");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();

        System.out.println(result==member); // 방법1
        assertEquals(member, result); // 방법2
        assertThat(member).isEqualTo(result); // 방법3 -> 이걸 많이 씀
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1); // 정상 작동
        //assertThat(result).isEqualTo(member2); // 오류 작동
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
