package com.example.main;

import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.example.main.dtos.ListadoDTO;
import com.example.main.entities.Estadistica;
import com.example.main.entities.Humano;
import com.example.main.services.HumanoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ApiRestApplicationTests {

	private static final String BASE_URL = "/api/v1/mutant/";

	@Autowired
	private HumanoService humanoService;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private MockMvc mvc;

	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void initTests() {
		// Always start from known state,
		// in this case 1 row in hero table.
		jdbcTemplate.execute("delete from humano_adn; delete from apirest_humano;");
	}

	@Test
	public void contextLoads() {
		assertThat(jdbcTemplate).isNotNull();
		assertThat(mvc).isNotNull();
	}

	// ControllerTests
	@Test
	public void shouldValidateMutant() throws Exception {

		String humanoJson = "{\n" + "\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]\n"
				+ "}";

		MvcResult results = invokeIsMutant(humanoJson).andExpect(status().isOk())
				.andExpect(jsonPath("$.mutant", is(true))).andReturn();

		invokeGetStats().andExpect(status().isOk())
				.andExpect(content().string("'count_mutant_dna': 1, 'count_human_dna':1, 'ratio': 1.0")).andReturn();
		
//		invokeGetOne(1).andExpect(status().isOk()).andExpect(jsonPath("$.mutante", is(true)));
	}
	
	@Test
	public void shouldNotValidateMutant() throws Exception {

		String humanoJson = "{\n" + "\"dna\":[\"ATGCGA\",\"CGGTGC\",\"TTATGT\",\"AGAAGG\",\"CTCCTA\",\"TCACTG\"]\n"
				+ "}";

		MvcResult results = invokeIsMutant(humanoJson).andExpect(status().isForbidden())
				.andExpect(jsonPath("$.mutant", is(false))).andReturn();
		
		invokeGetStats().andExpect(status().isOk())
				.andExpect(content().string("'count_mutant_dna': 0, 'count_human_dna':1, 'ratio': 0.0")).andReturn();

//		invokeGetOne(1).andExpect(status().isOk()).andExpect(jsonPath("$.mutante", is(false)));
	}

	@Test
	public void shouldReturn400() throws Exception {

		String humanoJson = "{\n" + "\"dna\":[\"ATGCGA\",\"TTATGT\",\"AGAAGG\",\"CTCCTA\",\"TCACTG\"]\n"
				+ "}";

		MvcResult results = invokeIsMutant(humanoJson).andExpect(status().isBadRequest())
				.andExpect(content().string("'Error': 'La matriz no cumple con NxN'")).andReturn();	
		
		humanoJson = "{\n" + "\"pedro\":[\"ATGCGA\",\"CGGTGC\",\"TTATGT\",\"AGAAGG\",\"CTCCTA\",\"TCACTG\"]\n"
				+ "}";

		invokeIsMutant(humanoJson).andExpect(status().isBadRequest())
				.andExpect(content().string("'Error': 'El formato de la matriz no es valido, asegurese de nombrar la variable dna'")).andReturn();	
	}
	
	@Test
	public void shouldGetStats() throws Exception {

		MvcResult results = invokeGetStats().andExpect(status().isOk())
				.andExpect(content().string("'count_mutant_dna': 0, 'count_human_dna':0, 'ratio': NaN")).andReturn();

	}

	private ResultActions invokeIsMutant(String humanoJson) throws Exception {
		return mvc.perform(post(BASE_URL).content(humanoJson).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
	}

	private ResultActions invokeGetOne(int id) throws Exception {
		return mvc.perform(get(BASE_URL + id).accept(MediaType.APPLICATION_JSON));
	}

	private ResultActions invokeGetStats() throws Exception {
		return mvc.perform(get(BASE_URL + "/stats").accept(MediaType.APPLICATION_JSON));
	}

	
	// ServiceTests
	@Test
	public void testGetStats() {
		Estadistica stats = humanoService.getStats();
		assertThat(stats.getMutantDna() <= stats.getHumanDna());
		assertThat(stats.ratio()).isNotNull();
	}
	
	@Test
	public void testValidarAdn() {
		String[] dna = {
	            "AAAAGA",
	            "ACGGTC",
	            "TTGTGT",
	            "AGAAGG",
	            "CAGCTA",
	            "TCACTG"};
		ListadoDTO listaDTO = new ListadoDTO();
		listaDTO.dna = dna;
		
		assertThat(humanoService.isMutant(listaDTO)).isTrue();
	}
	
	
	//Entity
	@Test
	public void testSetMutante() {
		Humano hum = new Humano();
		hum.setMutante(false);
		assertThat(hum.isMutant()).isFalse();
	}

}
