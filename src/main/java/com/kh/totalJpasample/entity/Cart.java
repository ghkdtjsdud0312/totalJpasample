package com.kh.totalJpasample.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// 장바구니
@Entity
@Getter
@Setter
@Table(name = "cart")
public class Cart {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String CartName;

    @OneToOne // 회원 엔티티와 일대일 매핑
    @JoinColumn(name = "member_id") // 객체지향문법이라 객체를 넣어줘야 함
    private Member member;


}
