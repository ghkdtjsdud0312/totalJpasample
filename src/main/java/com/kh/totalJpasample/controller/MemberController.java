package com.kh.totalJpasample.controller;

import com.kh.totalJpasample.dto.MemberDto;
import com.kh.totalJpasample.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kh.totalJpasample.utils.Common.CORS_ORIGIN;

@Slf4j //Log f4 그랩 깐 것이며 로그를 기록, 출력할 때 사용 /로그를 쌓기 위한 것
@CrossOrigin(origins = CORS_ORIGIN)
@RestController
@RequestMapping("/users") // 경로 지정
@RequiredArgsConstructor // 생성자 만듦
public class MemberController {
    private final MemberService memberService;
    // 회원 등록
    @PostMapping("/new") // user의 new
    public ResponseEntity<Boolean> memberRegister(@RequestBody MemberDto memberDto) {//user의 new에 memberRegister가 불림/ RequestBody은 프론트엔드를 불러줌
        // 제어 역전
    boolean isTrue = memberService.saveMember(memberDto);
    return ResponseEntity.ok(isTrue);
    }
    // 회원 전체 조회
    @GetMapping("/list")
    public ResponseEntity<List<MemberDto>> memberList() {
        List<MemberDto> list = memberService.getMemberList();
        return ResponseEntity.ok(list);
    }
    // 회원 상태 조회
    @GetMapping("/detail/{email}")
     public ResponseEntity<MemberDto> memberDetail(@PathVariable String email) {
        MemberDto memberDto = memberService.getMemberDetail(email);
        return ResponseEntity.ok(memberDto);
    }
}
