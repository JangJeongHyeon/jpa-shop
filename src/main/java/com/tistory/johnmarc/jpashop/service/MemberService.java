package com.tistory.johnmarc.jpashop.service;

import com.tistory.johnmarc.jpashop.domain.Member;
import com.tistory.johnmarc.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        // member 객체가 영속화 되는 순간(ex: entityManager.persistent()) JPA는 PK 값을 생성함, DB에 저장되지 않은 상태여도 생성
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> members = memberRepository.findByName(member.getName());
        if(!members.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    // 회원전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // 단일 회원 조회
    public Member findMember(long id){
        return memberRepository.findOne(id);
    }
}
