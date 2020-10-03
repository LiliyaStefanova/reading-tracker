package org.elstere.reading.tracker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@EnableTransactionManagement
public class ReadingTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadingTrackerApplication.class, args);
	}

}
