package com.kh.totalJpasample.service;

import com.kh.totalJpasample.dto.MemberDto;
import com.kh.totalJpasample.entity.Member;
import com.kh.totalJpasample.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service // 해당 객체를 빈으로 등록 되어 객체 생성 없이 가능
@RequiredArgsConstructor // 매개변수가 전부 포함된 생성자를 자동으로 만들어줌
public class MemberService {
    private final MemberRepository memberRepository; // 의존성 주입
    // 회원 등록
    // 이미 등록된 회원인지 확인하는 쿼리문
    public boolean saveMember(MemberDto memberDto) {

        boolean isReg = memberRepository.existsByEmail(memberDto.getEmail());
        if(isReg) return false;

        Member member = new Member(); // 의존성 주입을 안함
        member.setEmail(memberDto.getEmail());
        member.setPassword(memberDto.getPassword());
        member.setName(memberDto.getName());
        memberRepository.save(member);
        return true;
    }
    // 회원 전체 조회
    public List<MemberDto> getMemberList() {
        List<MemberDto> memberDtos = new ArrayList<>();
        List<Member> members = memberRepository.findAll(); //findAll = select all / members에 전체 리스트가 담긴다.
        for(Member member : members) {
            memberDtos.add(convertEntityToDto(member));
        }
        return memberDtos;
    }

    // 회원 상세 조회
    public MemberDto getMemberDetail(String email) { // repository에 optional<>추가 해줘야 orElseThrow 오류안남
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));
        return convertEntityToDto(member); // 있으면 해당 회원 나오고 없으면 해당 회원 안나옴
    }

    // 회원 엔티티를 DTO로 변환하는 메소드 만들기
    private MemberDto convertEntityToDto(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setEmail(member.getEmail());
        memberDto.setPassword(member.getPassword());
        memberDto.setName(member.getName());
        memberDto.setRegDate(member.getRegDate());
        return memberDto;
    }

}
