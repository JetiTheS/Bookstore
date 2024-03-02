package com.example.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookrepository, CategoryRepository categoryrepository) {
		return (args) -> {

			log.info("tallennetaan pari kirjaa ja kategoriat");
			Category category1 = new Category("Scifi");
			Category category2 = new Category("Kauhu");
			Category category3 = new Category("Fantasia");
			Category category4 = new Category("Manga");

			categoryrepository.save(category1);
			categoryrepository.save(category2);
			categoryrepository.save(category3);
			categoryrepository.save(category4);

			bookrepository.save(new Book("Elämänkerta", "Minä", "2023", "12345", "30", category2));
			bookrepository.save(new Book("Joku", "Kate", "1992", "123", "50", category3));

			log.info("Haetaan kaikki kirjat");
			for (Book book : bookrepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
