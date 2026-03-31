package com.myspringboot.sajo.member;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member_kakao")
public class MemberKakao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqMemKakao")
    @SequenceGenerator(name = "seqMemKakao", sequenceName = "seq_member_kakao_no", allocationSize = 1)
    private Integer id; // MemberKakao 자체의 PK (선택사항)

    // Member 엔티티의 PK(memberNo)를 참조하는 외래키(FK) 설정
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no", referencedColumnName = "memberNo", nullable = false)
    private Member member;

    // 카카오에서 제공하는 고유 ID (Long 타입)
    @Column(nullable = false, unique = true)
    private Long kakaoId;
}