package com.example.library_management.Service;

import com.example.library_management.Model.Books;
import com.example.library_management.Repository.BookRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;

    }

    public List<Books> getAllBooks() {
        // Implement logic to get all books from repository
        return bookRepository.findAll();
    }

    public Books getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException("Book not found with id: " + id, 0));
    }

    public Books createBook(Books book) {
        if (book.getId() != null) {
            throw new IllegalArgumentException("The ID must be null for a new entity");
        }
        // Implement logic to create book in repository
        return bookRepository.save(book);
    }

    public Books updateBook(Long id, Books updatedBook) {
        // Implement logic to update book in repository
        Books existingBook = getBookById(id);
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setPublishedDate(updatedBook.getPublishedDate());
        existingBook.setAvailableCopies(updatedBook.getAvailableCopies());
        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id) {
        // Implement logic to delete book from repository
        bookRepository.deleteById(id);
    }

    public List<Books> searchBooks(String query) {
        // Implement logic to search books by title, author, or isbn in repository
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrIsbnContainingIgnoreCase(query, query, query);
    }

}
