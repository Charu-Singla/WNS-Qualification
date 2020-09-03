package com.test.wns.service;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.test.wns.model.FinalCritExt;
import com.test.wns.repository.WnsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(PowerMockRunner.class)
public class WnsServiceTest {
	
	@InjectMocks
	WnsService service;
	
	@Spy
	WnsRepository repository;
	
	@Spy
	CSVFileWriter writer;
	
	@Spy
	EntityManager entityManager;
	
	

	@BeforeEach
	void setUp() {

		MockitoAnnotations.initMocks(this);		
	}
	
	//@Test
	public void generateCsvFileTest() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		
		FinalCritExt entity = new FinalCritExt();
		entity.setAcceptcrit("Y");
		Mockito.when(repository.findAll()).thenReturn(List.of(entity));
		
		
		service.generateCsvFile();
		verify(writer, times(1)).writeDataAtOnce(any());
		
	}
	

}
