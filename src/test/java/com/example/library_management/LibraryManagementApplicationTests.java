package com.example.library_management;

import com.example.library_management.Controller.BookController;
import com.example.library_management.Model.Books;
import com.example.library_management.Service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class LibraryManagementApplicationTests {

	@Mock
	private BookService bookService;

	@InjectMocks
	private BookController bookController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllBooks() {
		List<Books> books = new ArrayList<>();
		// Add some dummy books to the list
		books.add(new Books(1L, "Book 1", "Author 1", "1234567890", "2024-01-01", 5));

		when(bookService.getAllBooks()).thenReturn(books);

		ResponseEntity<List<Books>> responseEntity = bookController.getAllBooks();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(books, responseEntity.getBody());
	}

}
