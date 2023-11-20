package com.kh.totalJpasample.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDateTime;
// 실제 디비 데이터 만드는 곳
@Entity // 영속성
@Table(name = "member") // 연결해서 쓰면 언더바 사용
@Getter // @Date 하면 모두를 의미
@Setter
@ToString
public class Member {
    @Id // primary키 역할
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false) // null을 허용하지 않음
    private String name;
    private String password;
    @Column(unique = true) // 유일한 값이어야 함
    private String email;
    private LocalDateTime regDate; // 가입일
    @PrePersist // 디비에서 먼저 하기 전에 날짜를 적용해서 넣는 영속성
    public void prePersist() {
        regDate = LocalDateTime.now(); // 자동 삽입
    }
}
