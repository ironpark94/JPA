package jpabook.jpashop.repository;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;

@Repository
public class MemberRepository {
    
    @PersistenceContext
    EntityManager em;

    public Long save(Member member){
        //member를 리턴하지 않고 ID를 리턴하는이유?
        //커멘드와 쿼리를 분리하는 전략
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }
}
