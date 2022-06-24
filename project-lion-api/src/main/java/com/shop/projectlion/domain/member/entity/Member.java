package com.shop.projectlion.domain.member.entity;


import com.shop.projectlion.domain.base.BaseEntity;
import com.shop.projectlion.domain.member.constant.MemberRole;
import com.shop.projectlion.domain.member.constant.MemberType;
import com.shop.projectlion.global.util.DateTimeUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private MemberType memberType;

    @Column(unique = true, length = 50, nullable = false)
    private String email;

    @Column(length = 200)
    private String password;

    @Column(nullable = false, length = 20)
    private String memberName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MemberRole memberRole;

    @Column(length = 250)
    private String refreshToken;

    private LocalDateTime tokenExpirationTime;

    @Builder
    public Member(MemberType memberType, String email, String password, String memberName, MemberRole memberRole, String refreshToken, LocalDateTime tokenExpirationTime) {

        this.memberType = memberType;
        this.email = email;
        this.password = password;
        this.memberName = memberName;
        this.memberRole = memberRole;
        this.refreshToken = refreshToken;
        this.tokenExpirationTime = tokenExpirationTime;
    }

    public static Member createMember(Member member) {
        return Member.builder()
                .memberType(member.getMemberType())
                .email(member.getEmail())
                .password(member.getPassword())
                .memberName(member.getMemberName())
                .memberRole(member.getMemberRole())
                .refreshToken(member.getRefreshToken())
                .tokenExpirationTime(member.getTokenExpirationTime())
                .build();
    }

    public void reRegisterRefreshToken(String refreshToken, Date refreshTokenExpirationTime){
        this.refreshToken = refreshToken;
        this.tokenExpirationTime = DateTimeUtils.convertToLocalDateTime(refreshTokenExpirationTime);
    }

    public void expireRefreshToken(){
        this.tokenExpirationTime = LocalDateTime.now();
    }

}
