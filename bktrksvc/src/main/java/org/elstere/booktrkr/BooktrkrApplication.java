package org.elstere.booktrkr;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elstere.booktrkr.dao.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.Date;
import java.time.LocalDate;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@EnableTransactionManagement
@EnableConfigurationProperties(GithubProperties.class)
public class BooktrkrApplication  {

	public static void main(String[] args) {
		SpringApplication.run(BooktrkrApplication.class, args);
	}

}
