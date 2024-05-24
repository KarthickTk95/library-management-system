package com.example.library_management.Repository;

import com.example.library_management.Model.Borrows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BorrowRepository extends JpaRepository<Borrows, Long> {


    List<Borrows> findByBookId(Long bookId);

    List<Borrows> findByMemberId(Long memberId);
}
