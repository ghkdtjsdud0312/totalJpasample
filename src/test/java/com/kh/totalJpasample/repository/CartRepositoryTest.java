package com.kh.totalJpasample.repository;

import com.kh.totalJpasample.entity.Cart;
import com.kh.totalJpasample.entity.Member;
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
import java.time.LocalDateTime;

@SpringBootTest // spring 컨텍스트를 로드하여 테스트 환경 설정
@Transactional // 데이터베이스의 논리적인 작업 단위, 모두 성공이 아니면 롤백(테스트쪽은 transition이 있든지 없어도 무조건 모두 롤백), Transactional는 자바와 스트링부트꺼 아무거나 써도 무방
@Slf4j //로깅 데이터를 처리하기 위해 사용, 안되면 gradle 돌려주면 됨, log4j -> 로그(해킹,사용자가 어떤 행동하고 오류가 남았는지 기록을 남기기 위해, 로그데이터 저장)를 남기기 위해서 사용
@TestPropertySource(locations = "classpath:application-test.properties")


class CartRepositoryTest { // transition이 걸려있어도 test는 성공하더라도 rollback을 함(콘솔: Rolled back transaction for test)
   @Autowired // 생성자가 안 만들어짐, RequiredArgsConstructor 넣으면 생성자 만들어지면 Autowired 사용 안해도 됨
   CartRepository cartRepository;
   @Autowired
   MemberRepository memberRepository;
    @PersistenceContext // JPA의 EntityManager을 주입 받음
    EntityManager em;
    // 회원 엔티티 생성(장바구니)
    public Member createMemberInfo() {
        Member member = new Member();
        member.setUserId("jks2024");
        member.setPassword("sphb8250");
        member.setName("곰돌이사육사");
        member.setEmail("jks2024@gmail.com");
        member.setRegDate(LocalDateTime.now()); // 현재 시간 찍음, 백에서 찍고 프론트에서 바꿔주면 잘 찍힘
        return member;
    }
    @Test
    @DisplayName("장바구니 회원 매핑 테스트")
    public void findCartAndMemberTest() {
        Member member = createMemberInfo();
        memberRepository.save(member);

        Cart cart = new Cart();
        cart.setCartName("마켓컬리 장바구니");
        cart.setMember(member);
        cartRepository.save(cart);

        em.flush(); // 영속성 컨텍스트에 데이터 저장 후 트랜잭션이 끝날 때 데이터베이스에 기록, 즉시 반영
        em.clear(); // 영속성 컨텍스트를 비움

        Cart saveCart = cartRepository.findById(cart.getId()).orElseThrow(EntityNotFoundException::new); // 없으면 예외처리 해달라는 뜻
        System.out.println(saveCart);
    }
}