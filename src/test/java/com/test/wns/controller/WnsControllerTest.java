package com.test.wns.controller;

import com.test.wns.dto.FinalCritExtDTO;
import com.test.wns.service.CSVFileWriter;
import com.test.wns.service.WnsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WnsControllerTest {

	@InjectMocks
	WnsController controller;

	@MockBean
	WnsService service;

	
	@MockBean
	CSVFileWriter writer;
	
	@Autowired
	MockMvc mockMvc;

	
	@Test
	public void testGenerateCsv() throws Exception {
		
		FinalCritExtDTO response = new FinalCritExtDTO();
		response.setVehcat("CI");
		Mockito.when(service.generateDataForCsvFile()).thenReturn(List.of(response));
		mockMvc.perform(get("/generatecsv")).andExpect(content().string(containsString("SUCCESS")));
		
		verify(writer, times(1)).writeDataAtOnce(any());
	}

}
