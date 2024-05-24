package com.example.library_management.Repository;

import com.example.library_management.Model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository <Books,Long> {

   // @Query("SELECT b FROM Book b WHERE lower(b.title) LIKE %:query% OR lower(b.author) LIKE %:query% OR lower(b.isbn) LIKE %:query%")
    List<Books> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrIsbnContainingIgnoreCase(String query, String query1, String query2);
}
