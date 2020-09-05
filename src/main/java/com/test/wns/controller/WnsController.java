package com.test.wns.controller;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.test.wns.dto.FinalCritExtDTO;
import com.test.wns.service.CSVFileWriter;
import com.test.wns.service.WnsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * This is controller class where end point is written to fetch data and write
 * to csv file
 * 
 * @author charusingla
 *
 */
@RestController
public class WnsController {

	@Autowired
	WnsService service;

	@Autowired
	CSVFileWriter writer;
	
	private static final Logger logger = LoggerFactory.getLogger(WnsService.class);

	/**
	 * This is endpoint definition for generating csv file after calling stored
	 * procedure and return success as string if file is generated successfully
	 * 
	 * @throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException
	 */
	@RequestMapping("/generatecsv")
	public String generateCsvFile() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

		List<FinalCritExtDTO> list = service.generateDataForCsvFile();

		if (Objects.nonNull(list) && !list.isEmpty()) {
			logger.info("data fetched from db and mapped of size " + list.size());
			writer.writeDataAtOnce(list);
		} else
			logger.info("No records found in db to be written");
		logger.debug("File successfully generated");
		return "SUCCESS";
	}

}
