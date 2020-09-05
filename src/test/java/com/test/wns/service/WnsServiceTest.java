package com.test.wns.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.test.wns.dto.FinalCritExtDTO;
import com.test.wns.model.MigrateTest;
import com.test.wns.repository.WnsRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
public class WnsServiceTest {

	@InjectMocks
	WnsService service;

	@Spy
	WnsRepository repository;

	@BeforeEach
	void setUp() {

		MockitoAnnotations.initMocks(this);
	}
	

	public final static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	@Test
	public void generateDataForCsvFileTest() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

		MigrateTest entity = new MigrateTest();
		entity.setNvic("01F624");
		entity.setVehcat("CI");
		entity.setGiociacpt("ACCEPTABLE");
		entity.setGiocirule("N");
		
		Mockito.when(repository.findAll()).thenReturn(List.of(entity));

		List<FinalCritExtDTO> response = service.generateDataForCsvFile();
		
		Assertions.assertNotNull(response);
		Assertions.assertEquals("CI", response.get(0).getVehcat());
		Assertions.assertEquals("GIOCI", response.get(0).getCompany());
		Assertions.assertEquals("ADD", response.get(0).getAddmod());
		
		Assertions.assertEquals("ACCEPTABLE", response.get(0).getAcceptcrit());
		Assertions.assertEquals("N", response.get(0).getInternetjep());
	}
	
	@Test
	public void generateDataForCsvFileGUTest() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

		MigrateTest entity = new MigrateTest();
		entity.setNvic("01F624");
		entity.setVehcat("GU");
		entity.setGiociacpt("ACCEPTABLE");
		entity.setGiocirule("N");
		entity.setJciacpt("ACCEPTABLE");
		entity.setJcirule("N");
		
		Mockito.when(repository.findAll()).thenReturn(List.of(entity));

		List<FinalCritExtDTO> response = service.generateDataForCsvFile();
		
		Assertions.assertNotNull(response);
		Assertions.assertEquals(2, response.size());
		Assertions.assertEquals("GU", response.get(0).getVehcat());
		Assertions.assertEquals("GIOCI", response.get(0).getCompany());
		Assertions.assertEquals("ADD", response.get(0).getAddmod());
		
		Assertions.assertEquals("JCI", response.get(1).getCompany());

	}

	@Test
	public void generateDataForCsvFileGUCompleteTest() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

		MigrateTest entity = GSON.fromJson(
				TestUtils.getStringFromFile("migrateTestGu.json"), MigrateTest.class);

		
		FinalCritExtDTO expectedEntity = GSON.fromJson(
				TestUtils.getStringFromFile("finalCritExt.json"), FinalCritExtDTO.class);
		
		expectedEntity.setEffectivedate(getEffectiveDate());
		expectedEntity.setChangetimestamp(getChangeTimeStamp());
		
		Mockito.when(repository.findAll()).thenReturn(List.of(entity));

		List<FinalCritExtDTO> response = service.generateDataForCsvFile();
		
		Assertions.assertNotNull(response);
		Assertions.assertEquals(12, response.size());
		
		//System.out.println(expectedEntity.equals(response.get(0)));
		Assert.assertEquals(expectedEntity, response.get(0));

	}

	

	/**
	 * @return effective date 24months older than current date and append 01 at end
	 */
	String getEffectiveDate() {
		DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("yyyyMM");
		LocalDateTime datetime = LocalDateTime.now();
		datetime = datetime.minusMonths(24);
		return datetime.format(newPattern) + "01";

	}

	/**
	 * @return change time stamp date 24months older than current date and append
	 *         01000000 at end
	 */
	String getChangeTimeStamp() {
		DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("yyyyMM");
		LocalDateTime datetime = LocalDateTime.now();
		datetime = datetime.minusMonths(24);
		return datetime.format(newPattern) + "01000000";

	}
	
}
