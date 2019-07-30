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

	public static void main(String[] args) {
		SpringApplication.run(BooktrkrApplication.class, args);
	}

	@Override
	public void run(String... args){
	}
}
