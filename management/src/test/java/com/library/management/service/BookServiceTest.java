package com.library.management.service;

import com.library.management.entity.Book;
import com.library.management.repo.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBooks() {
        // Mocking the behavior of the bookRepository.findAll() method
        List<Book> bookList = Arrays.asList(
                new Book("Book 1", "Author 1", 1997, "1223343087"),
                new Book("Book 2", "Author 2", 2018, "29347082374")
        );
        when(bookRepository.findAll()).thenReturn(bookList);

        // Calling the getAllBooks() method of the bookService
        List<Book> result = bookService.getAllBooks();

        // Verifying the expected result
        assertEquals(bookList, result);
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void testGetBookById() {
        // Mocking the behavior of the bookRepository.findById() method
        Long bookId = 1L;
        Book book = new Book("Book 1", "Author 1", 2011, "1231221");
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        // Calling the getBookById() method of the bookService
        Optional<Book> result = bookService.getBookById(bookId);

        // Verifying the expected result
        assertEquals(Optional.of(book), result);
        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    public void testSaveBook() {
        // Mocking the behavior of the bookRepository.save() method
        Book book = new Book("Book 1", "Author 1", 1777, "2834897389");
        when(bookRepository.save(book)).thenReturn(book);

        // Calling the saveBook() method of the bookService
        Book result = bookService.saveBook(book);

        // Verifying the expected result
        assertEquals(book, result);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testDeleteBookById() {
        // Calling the deleteBookById() method of the bookService
        Long bookId = 1L;
        bookService.deleteBookById(bookId);

        // Verifying that the bookRepository.deleteById() method was called
        verify(bookRepository, times(1)).deleteById(bookId);
    }

    @Test
    public void testSearchBooks() {
        // Mocking the behavior of the bookRepository.findByTitleAndAuthor() method
        String title = "Book 1";
        String author = "Author 1";
        List<Book> bookList = Arrays.asList(new Book(title,author, 1667, "27364723896"));
        when(bookRepository.findByTitleAndAuthor(title, author)).thenReturn(bookList);

        // Calling the searchBooks() method of the bookService
        List<Book> result = bookService.searchBooks(title, author);

        // Verifying the expected result
        assertEquals(bookList, result);
        verify(bookRepository, times(1)).findByTitleAndAuthor(title, author);
    }

    // Add more test methods for other functionalities in the BookService class

}

