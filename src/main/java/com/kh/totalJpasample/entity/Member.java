package com.kh.totalJpasample.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // 영속성
@Table(name = "member") // 연결해서 쓰면 언더바 사용
@Getter // @Date 하면 모두를 의미
@Setter
@ToString
public class Member {
    @Id // primary키 역할
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;
    @Column(unique = true)
    private String email;
    private LocalDateTime regDate; // 가입일
}
