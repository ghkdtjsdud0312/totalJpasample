package com.kh.totalJpasample.entity;

import com.kh.totalJpasample.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

// DB Table 만드는 곳(1번으로 만듦)
@Getter
@Setter
@ToString
@Entity // 클래스를 엔티티로 선언
@Table(name = "item") // 엔티티와 매핑 할 테이블을 지정
public class Item {
    @Id // 테이블의 기본 키 지정
    @Column(name = "item_id") // 필드와 컬럼을 매핑
    @GeneratedValue(strategy = GenerationType.AUTO) // auto는 jpa구현체(hybernate) 자동으로 생성전략을 짜줌 / jpa가 자동으로 생성 전략을 결정
    private Long id; // 상품 코드

    @Column(nullable = false, length = 50) // null을 허용하지 않고 길이를 50자로 제한
    private String itemName; // 상품명 item_name

    @Column(name = "price", nullable = false)
    private int price; // 가격

    @Column(nullable = false)
    private int stockNumber; // 재고 수량

    @Lob // 긴 데이터형을 만들 때 사용(영화 자막에 사용)
    @Column(nullable = false)
    private String itemDetail; // 상품 상세 설명

    // EnumType.STRING : ENUM으로 정의된 값을 문자열로 DB에 저장
    @Enumerated(EnumType.STRING) // 실제 디비에는 sell이라고 저장됨 /ordinal 일련번호 순서대로 저장
    private ItemSellStatus itemSellStatus;
    private LocalDateTime regTime; // 등록 시간
    private LocalDateTime updateTime; // 수정 시간
}
