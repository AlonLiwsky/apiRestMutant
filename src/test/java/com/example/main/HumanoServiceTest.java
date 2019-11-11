package com.example.main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.main.entities.Estadistica;
import com.example.main.services.HumanoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HumanoServiceTest {

	@Autowired
	HumanoService humanoService;
	
	@Test
	public void testGetStats() {
		Estadistica stats = humanoService.getStats();
		assertThat(stats.getMutantDna() <= stats.getHumanDna());
		assertThat(stats.ratio()).isNotNull();
	}
	
}
