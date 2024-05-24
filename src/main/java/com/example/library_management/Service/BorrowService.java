package com.example.library_management.Service;

import com.example.library_management.Model.Books;
import com.example.library_management.Model.Borrows;
import com.example.library_management.Model.Members;
import com.example.library_management.Repository.BookRepository;
import com.example.library_management.Repository.BorrowRepository;
import com.example.library_management.Repository.MemberRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowService {
    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public BorrowService(BorrowRepository borrowRepository, BookRepository bookRepository, MemberRepository memberRepository) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    public List<Borrows> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public Borrows getBorrowById(Long id) {
        return borrowRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException("Borrow not found with id: " + id, 0));
    }

    public Borrows borrowBook(Long memberId, Long bookId) {
        Members member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EmptyResultDataAccessException("Member not found with id: " + memberId, 0));

        Books book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EmptyResultDataAccessException("Book not found with id: " + bookId, 0));

        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Book is not available for borrowing");
        }

        Borrows borrow = new Borrows();
        borrow.setMember(member);
        borrow.setBook(book);
        borrow.setBorrowedDate(LocalDate.parse(LocalDate.now().toString()));
        borrow.setDueDate(LocalDate.parse(LocalDate.now().plusDays(14).toString())); // Assuming a 14-day borrowing period

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        return borrowRepository.save(borrow);
    }

    public Borrows returnBook(Long borrowId) {
        Borrows borrow = getBorrowById(borrowId);

        Books book = borrow.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        borrowRepository.deleteById(borrowId);

        return borrow;
    }

    public List<Borrows> getBorrowsByMemberId(Long memberId) {
        return borrowRepository.findByMemberId(memberId);
    }

    public List<Borrows> getBorrowsByBookId(Long bookId) {
        return borrowRepository.findByBookId(bookId);
    }

}
