package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.bookstore.web.BookController;
import com.example.bookstore.web.CategoryController;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class BookstoreApplicationTests {
	@Autowired
	private BookController bookcontroller;
	private CategoryController categorycontroller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(bookcontroller).isNotNull();
		assertThat(categorycontroller).isNull();
	}
}
