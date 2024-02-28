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
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository categoryrepository) {
		return (args) -> {
			log.info("tallennetaan pari kirjaa ja kategoriat");
			repository.save(new Book("Elämänkerta", "Minä", "2023", "12345", "30"));
			repository.save(new Book("Joku", "Kate", "1992", "123", "50"));

			categoryrepository.save(new Category("Scifi"));
			categoryrepository.save(new Category("Kauhu"));
			categoryrepository.save(new Category("Fantasia"));
			categoryrepository.save(new Category("Manga"));

			log.info("Haetaan kaikki kategoriat");
			for (Category category : categoryrepository.findAll()) {
				log.info(category.toString());
			}

			log.info("Haetaan kaikki kirjat");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
