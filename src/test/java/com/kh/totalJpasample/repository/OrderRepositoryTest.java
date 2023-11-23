package com.kh.totalJpasample.repository;

import com.kh.totalJpasample.constant.ItemSellStatus;
import com.kh.totalJpasample.entity.Item;
import com.kh.totalJpasample.entity.Member;
import com.kh.totalJpasample.entity.Order;
import com.kh.totalJpasample.entity.OrderItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 테스트 자체가 롤백이라 디비에 저장이 안되고 자체 기록도 안남고 마지막에 자동으로 롤백됨
@Transactional // 데이터베이스의 논리적인 작업단위 ,모두 성공이 아니면 롤백
@Slf4j
@TestPropertySource(locations = "classpath:application-test.properties")
class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @PersistenceContext
    EntityManager em;

    public Item createItem() { // 제품을 만듦
        Item item = new Item();
        item.setItemName("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        return item;
    }

    @Test
    @DisplayName("영속성 전의 테스트")
    public void cascadeTest() {
        Order order = new Order();
        for (int i = 0; i < 3; i++) {
            Item item = this.createItem(); // createItem에 불러서 item에 들어감
            itemRepository.save(item);
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setCount(10);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order);
            order.getOrderItemList().add(orderItem);
        }
        // 엔티티를 저장하면서 DB에 반영
        orderRepository.saveAndFlush(order); // save는 영속성 엔티티로 save 즉시 디비에 반영해달라는 뜻
        em.clear(); // 영속성 상태를 초기화

        // 주문 엔티티 조회
        Order saveOrder = orderRepository.findById(order.getId()).orElseThrow(EntityNotFoundException::new);
        // order 객체의 orderItem 개수가 3개 인지 확인
        log.warn(String.valueOf(saveOrder.getOrderItemList().size())); //log가 기록하는 것
    }

    // 주문 목록 만들기
    public Order createOrder() {
        Order order = new Order();
        for(int i = 0; i < 3; i++) {
            Item item = createItem(); // orderItem 3개 만들어짐
            itemRepository.save(item);
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setCount(10);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order);
            order.getOrderItemList().add(orderItem);
        }
        Member member = new Member();
        member.setName("곰돌이사육사");
        member.setEmail("jks2024@gmail.com");
        memberRepository.save(member);

        order.setMember(member);
        orderRepository.save(order);
        return order;
    }
    @Test
    @DisplayName("고아 객체 제거 테스트")
    public void orphanRemovalTest() {
        Order order = this.createOrder();
        order.getOrderItemList().remove(0); //0번째 항목지움
        em.flush();
    }

    @Test
    @DisplayName("지연 로딩 테스트")
    public void lazyLoadingTest(){
        Order order = this.createOrder();
        Long orderItemId = order.getOrderItemList().get(0).getId(); // 0번 객체의 아이디를 가져옴
        em.flush(); // 즉시 실행, 즉시 반영
        em.clear();
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(EntityNotFoundException::new); // null 안나오게하려고 optinal
        log.warn(String.valueOf(orderItem.getOrder().getClass()));

        // orderItem 엔티티에 있는 order 객체의 클래스를 출력 합니다.
        // 지연 로딩 확인 차 넣어봄
        log.warn(String.valueOf(orderItem.getOrder().getClass()));
        log.warn("================================================");
        log.warn(String.valueOf(orderItem.getOrder().getOrderDate()));
        log.warn("================================================");

    }
}