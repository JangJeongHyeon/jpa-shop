package com.tistory.johnmarc.jpashop.repository;

import com.tistory.johnmarc.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        // 멤버 엔티티 영속화, 트랜잭션 끝날 시 쿼리 전송
        em.persist(member);
    }

    public Member findOne(Long id) {
        // 단건 조회 (타입, PK)
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        // JQPL, 탐색 대상이 DB 테이블이 아닌 Entity Class 임
        return em.createQuery("SELECT m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){
        // JPQL, Parameter Binding
        return em.createQuery("SELECT m FROM Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
