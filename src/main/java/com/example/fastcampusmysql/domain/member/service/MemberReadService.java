package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.dto.MemberNicknameHistoryDto;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import com.example.fastcampusmysql.domain.member.repository.NicknameHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberReadService {
    final private MemberRepository memberRepository;
    final private NicknameHistoryRepository nicknameHistoryRepository;

//    public Member getMember(Long id){
//        return memberRepository.findById(id).orElseThrow();
//    }
        public MemberDto getMember(Long id){
        var member= memberRepository.findById(id).orElseThrow();
        return toDto(member);
    }

    public List<MemberNicknameHistoryDto> getNicknameHistories(Long memberId){
//            return null;

            return nicknameHistoryRepository.findAllByMemberId(memberId).stream().map(this::toDto).toList();
    }

    public MemberDto toDto(Member member){
        return new MemberDto(
                member.getId(),
                member.getEmail(),
                member.getNickname(),
                member.getBirthday());
    }

    private MemberNicknameHistoryDto toDto(MemberNicknameHistory history){
        return new MemberNicknameHistoryDto(
                history.getMemberId(),
                history.getId(),
                history.getNickname(),
                history.getCreatedat()
        );

    }

}