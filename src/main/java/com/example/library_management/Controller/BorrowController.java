package com.example.library_management.Controller;

import com.example.library_management.Model.BorrowRequest;
import com.example.library_management.Model.Borrows;
import com.example.library_management.Service.BorrowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrows")
public class BorrowController {

    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @GetMapping
    public ResponseEntity<List<Borrows>> getAllBorrows() {
        List<Borrows> borrows = borrowService.getAllBorrows();
        return ResponseEntity.ok(borrows);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Borrows> getBorrowById(@PathVariable Long id) {
        Borrows borrow = borrowService.getBorrowById(id);
        return ResponseEntity.ok(borrow);
    }

    @PostMapping("/borrow")
    public ResponseEntity<Borrows> borrowBook(@RequestBody BorrowRequest borrowRequest) {
        Borrows borrowedBook = borrowService.borrowBook(borrowRequest.getMemberId(), borrowRequest.getBookId());
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowedBook);
    }


    @PostMapping("/return/{borrowId}")
    public ResponseEntity<Borrows> returnBook(@PathVariable Long borrowId) {
        Borrows returnedBook = borrowService.returnBook(borrowId);
        return ResponseEntity.ok(returnedBook);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<Borrows>> getBorrowsByMemberId(@PathVariable Long memberId) {
        List<Borrows> borrows = borrowService.getBorrowsByMemberId(memberId);
        return ResponseEntity.ok(borrows);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Borrows>> getBorrowsByBookId(@PathVariable Long bookId) {
        List<Borrows> borrows = borrowService.getBorrowsByBookId(bookId);
        return ResponseEntity.ok(borrows);
    }
}
