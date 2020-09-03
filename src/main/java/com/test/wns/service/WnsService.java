package com.test.wns.service;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.test.wns.dto.FinalCritExtDTO;
import com.test.wns.mapper.AbstarctFinalCritExtMapper;
import com.test.wns.repository.WnsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import java.util.List;
import java.util.Objects;

/**
 * 
 * This is service class for performing business operation for writing the csv
 * file
 * 
 * @author charusingla
 *
 */
@Service
public class WnsService {

	@Autowired
	EntityManager entityManager;

	@Autowired
	WnsRepository repository;

	@Autowired
	CSVFileWriter writer;

	private static final Logger logger = LoggerFactory.getLogger(WnsService.class);
	/**
	 * Static mapper to map responseVo to response object.
	 */
	private static final AbstarctFinalCritExtMapper DOMAIN_MAPPER = AbstarctFinalCritExtMapper.INSTANCE;

	/**
	 * This method performs the following steps 1. Calls the stored procedure and
	 * populted final_crit_ext table 2. make databse call to fetch all data from the
	 * table 3. make call to csv writer to create the csv file
	 * 
	 * @throws CsvRequiredFieldEmptyException
	 * @throws CsvDataTypeMismatchException
	 */
	public void generateCsvFile() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

		this.callStoredProcedure();

		logger.info("Stored procedure successfully executed");

		// Map to domain object from list of data from db
		List<FinalCritExtDTO> list = DOMAIN_MAPPER.mapFinalCritExtDTOList(repository.findAll());

		if (Objects.nonNull(list)) {
			logger.info("data fetched from db and mapped of size " + list.size());
			writer.writeDataAtOnce(list);
		} else
			logger.info("No records found in db to be written");

	}

	private void callStoredProcedure() {
		// This block calls the stored procedure and execute it
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("TREFR_ACCEPT_CRIT");
		query.execute();

	}

}
