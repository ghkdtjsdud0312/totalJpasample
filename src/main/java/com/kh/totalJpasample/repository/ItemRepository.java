package com.kh.totalJpasample.repository;

// 2번째로 구현해줌 (데이터베이스 쿼리 실행/Repository은 jpa에 대한 상세 명세하면 규칙에 맞춰서 찾아주는 질문지)
//Repository의 상속 받는 것이 hybernate
import com.kh.totalJpasample.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// 기본적인 CRUD는 JpaRepository에 이미 정의 되어 있음, 페이징 처리도 포함 되어 있음
public interface ItemRepository extends JpaRepository<Item, Long> {

}
