package com.example.demo;

import com.example.demo.Errors.UserNotFoundException;
import com.example.demo.Services.GitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class DemoApplicationTests {

	@Autowired
	private GitService gitService;

	@Test
	void contextLoads() {
	}

	@Test
	void shouldReturnNotFoundForInvalidUser() {
		// given
		String username = "blalellelelelelelelelleleleldoesntexist";
		// when, then
		assertThrows(UserNotFoundException.class, () -> gitService.getAllRepos("blalellelelelelelelelleleleldoesntexist"));
	}

}
