package com.test.wns.exception;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.test.wns.service.WnsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomExceptionHandlerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	WnsService service;
	
	
	@Test
	void testException() throws Exception {
		Mockito.doThrow(CsvRequiredFieldEmptyException.class).when(service).generateCsvFile();
		mockMvc.perform(get("/generatecsv")).andExpect(content().string(containsString("CsvRequiredFieldEmptyException")));	
	}
	
	@Test
	void testExceptionCsvDataTypeMismatchException() throws Exception {
		Mockito.doThrow(CsvDataTypeMismatchException.class).when(service).generateCsvFile();
		mockMvc.perform(get("/generatecsv")).andExpect(content().string(containsString("CsvDataTypeMismatchException")));	
	}
	

}
