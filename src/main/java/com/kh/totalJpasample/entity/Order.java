package com.kh.totalJpasample.entity;

import com.kh.totalJpasample.constant.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") //order가 예약어로 걸려서 orders
public class Order {
    @Id
    @GeneratedValue // 기본 생성 전략은 auto
    @Column(name = "order_id")
    private  Long id;

    @ManyToOne
    @JoinColumn(name = "member_id") // 회원이 주인(one쪽이 주인)
    private Member member;

    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime regTime;
    private LocalDateTime updateTime;

    // 연관관계의 주인이 아님을 표시 함
    @OneToMany(mappedBy = "order") // mappedBy는 주인이 아니라는 뜻, oneToMany는 꼭 써줘야함
    private List<OrderItem> orderItemList = new ArrayList<>(); // 만들어진 정보만 불러옴
}
