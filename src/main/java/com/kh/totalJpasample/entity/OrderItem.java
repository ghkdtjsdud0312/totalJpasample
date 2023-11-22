package com.kh.totalJpasample.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class OrderItem { // cartItem과 같음
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order; //order가 주인

    private int orderPrice;
    private int count;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;
}
