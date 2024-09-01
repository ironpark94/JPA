package jpabook.jpashop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.assertj.core.api.Assertions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;

//junut에게 스피링과 연관된 테스트를 할것이다 라고 알리는 어노테이션
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {
    
    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testMember(){
        //given
        Member member = new Member();
        member.setUsername("memberA");

        //when
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        //then

        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

        // Assertions.assertThat(findMember).isEqualTo(member); //JPA 엔티티 통일성 보장

        //영속성 컨텍스트에 따라 트루가 나와야함.
        System.out.println("findMember == member: " + (findMember == member));
        
    }
}
