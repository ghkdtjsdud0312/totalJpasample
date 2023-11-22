package com.kh.totalJpasample.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_item_id")
    private Long id; // onetomany 관계

    @ManyToOne // 다대일 관계, 하나의 장바구니는 여러개의 상품을 담을 수 있음
    @JoinColumn(name = "cart_id")
    private Cart cart; // manytoone 관계(많이 쓰임)
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item; // 하나의 아이템은 여러 장바구니에 담길 수 있음(다대일 관계)

    private int count;

}
