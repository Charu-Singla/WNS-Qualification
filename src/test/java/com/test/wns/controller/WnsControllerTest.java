package com.test.wns.controller;

import com.test.wns.service.WnsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WnsControllerTest {

	@InjectMocks
	WnsController controller;

	@Mock
	WnsService service;

	
	@Autowired
	MockMvc mockMvc;

	
	@Test
	public void testGenerateCsv() throws Exception {
		Mockito.doNothing().when(service).generateCsvFile();
		mockMvc.perform(get("/generatecsv")).andExpect(content().string(containsString("SUCCESS")));
	}

}
