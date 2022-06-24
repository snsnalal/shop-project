package com.shop.projectlion.global.config;

import com.shop.projectlion.domain.member.entity.Member;
import com.shop.projectlion.global.error.exception.BusinessException;
import com.shop.projectlion.global.error.exception.ErrorCode;
import com.shop.projectlion.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class FormLoginServiceImpl implements UserDetailsService {

    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        Optional<Member> findMember = memberService.findMemberByEmail(userEmail);

        Member member;
        if(!findMember.isEmpty())
        {
            member = findMember.get();
        }
        else
        {
            throw new BusinessException(ErrorCode.LOGIN_ERROR);
        }


        /* 스프링 시큐리티 권한 부여 방식2
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("test@test.com").equals(userEmail)){
            authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        }
        else{
            authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
        }*/

        return new UserdetailsImpl(member);

    }

}
