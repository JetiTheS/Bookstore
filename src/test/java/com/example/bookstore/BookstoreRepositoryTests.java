package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;

@SpringBootTest(classes = BookstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class BookstoreRepositoryTests {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findByTitleShouldReturnBook() {
        List<Book> books = bookRepository.findByTitle("Joku");

        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Kate");
    }

    @Test
    public void createNewBook() {
        Category category = new Category("Hömppä");
        categoryRepository.save(category);
        Book book = new Book("Vitsikirja", "Hän", "2053", "1222", "35", category);
        bookRepository.save(book);
        assertThat(book.getId()).isNotNull();

    }

    @Test
    public void deleteNewBook() {
        List<Book> books = bookRepository.findByTitle("Joku");
        Book book = books.get(0);
        bookRepository.delete(book);
        List<Book> newBooks = bookRepository.findByTitle("Joku");
        assertThat(newBooks).hasSize(0);
    }
}
