package com.kh.totalJpasample.repository;

import com.kh.totalJpasample.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // 싱글톤으로 등록됨 / 자동으로 crud 만들어 둠
// 네이밍 규칙에 의해서 API를 작성하면 그에 맞는 쿼리문을 하이버네이트(JPA의 구현체)가 구현 해줌
public interface MemberRepository extends JpaRepository<Member, Long> { // 완전 추상화 개념(구현부 없어야하고 이름만등록하면 상속받는애가 무조건 구현해줘야 함 - 인터페이스 -> 하이버네이트(hibernate))
    Optional<Member> findByEmail(String email); //쿼리문 1개 만듦
    Member findByPassword(String pwd);
    Member findByEmailAndPassword(String email, String pwd); // 2개도 가능
    boolean existsByEmail(String email);

}
