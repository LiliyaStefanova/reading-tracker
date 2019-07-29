package org.elstere.booktrkr;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elstere.booktrkr.dao.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.sql.Date;
import java.time.LocalDate;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties(GithubProperties.class)
public class BooktrkrApplication implements CommandLineRunner {

	private final ReadingEntryRepository readingEntryRepository;
	private final AuthorRepository authorRepository;
	private final GenreRepository genreRepository;
	private final ReadingRecordRepository readingRecordRepository;

	public static void main(String[] args) {
		SpringApplication.run(BooktrkrApplication.class, args);
	}

	@Override
	public void run(String... args){

		Author author = new Author("G. Polya", "mathematician", "", "");
		Genre genre = new Genre("mathematics", "topics related to mathematical problems");

		authorRepository.save(author);
		Genre newGenre = genreRepository.save(genre);

		ReadingEntry readingEntryA = new ReadingEntry("How to solve it", "BOOK", "LETTERS",
				"English","Princeton","1", newGenre, new Authorship(author));

		readingEntryRepository.save(readingEntryA);

		Date startDate = Date.valueOf(LocalDate.of(2018, 1, 1));
		Date endDate = Date.valueOf(LocalDate.of(2019, 1, 1));

		ReadingRecord record = new ReadingRecord("Completed", 100.0, startDate, endDate, 1.0, "", readingEntryA);
		readingRecordRepository.save(record);

	}
}
