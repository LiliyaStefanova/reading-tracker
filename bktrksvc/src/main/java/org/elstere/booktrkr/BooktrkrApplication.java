package org.elstere.booktrkr;

import lombok.RequiredArgsConstructor;
import org.elstere.booktrkr.dao.*;
import org.hibernate.cfg.annotations.CollectionBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties(GithubProperties.class)
public class BooktrkrApplication implements CommandLineRunner {

	private final ReadingEntryRepository readingEntryRepository;
	private final AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(BooktrkrApplication.class, args);
	}

	@Override
	public void run(String... args){
//		Author authorA = new Author("Stephen King", "horror", "", "");
//		Author authorB = new Author("Donald Knuth", "comp sci", "", "algorithms book");
//
//		authorRepository.saveAll(Arrays.asList(authorA, authorB));
//
//		ReadingEntry entry1 = new ReadingEntry("The Art of Computer Programming", "BOOK", "LETTERS", "English", "Stanford", "3",
//				new Authorship(authorB));
//		ReadingEntry entry2 = new ReadingEntry("The Stand", "BOOK", "LETTERS", "English", "", "", new Authorship(authorA));
//
//		readingEntryRepository.save(entry1);
//		readingEntryRepository.save(entry2);
	}
}
