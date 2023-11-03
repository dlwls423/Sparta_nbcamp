package com.sparta.memo.repository;

import com.sparta.memo.entity.Memo;
import jakarta.persistence.OrderBy;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findAllByOrderByModifiedAtDesc();

    //2주차 숙제
    List<Memo> findAllByContentsContainsOrderByModifiedAtDesc(String keyword);
}