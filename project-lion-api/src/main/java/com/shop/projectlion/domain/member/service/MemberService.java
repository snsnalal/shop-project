package com.shop.projectlion.domain.member.service;


import com.shop.projectlion.domain.member.entity.Member;
import com.shop.projectlion.domain.member.repository.MemberRepository;
import com.shop.projectlion.global.error.exception.BusinessException;
import com.shop.projectlion.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public String join(Member member){

        validateDuplicateMember(member);

        memberRepository.save(member);

        return member.getEmail();
    }

    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }


    @Transactional
    public void validateDuplicateMember(Member member)
    {
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
        if (!findMember.isEmpty()){
            throw new BusinessException(ErrorCode.ALREADY_REGISTERED_MEMBER);
        }
    }

    public Optional<Member> findByRefreshToken(String refreshToken){
        return memberRepository.findByRefreshToken(refreshToken);
    }

}
