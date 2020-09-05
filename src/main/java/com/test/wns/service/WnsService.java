package com.test.wns.service;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.test.wns.dto.FinalCritExtDTO;
import com.test.wns.dto.MigrateTestDTO;
import com.test.wns.mapper.AbstarctFinalCritExtMapper;
import com.test.wns.repository.WnsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

;

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
	 * This method performs the following steps 1. Fetches test data from db and map
	 * to domain object 2. Generate three list based on various criteria and maks
	 * final filter and modifications 3. make call to csv writer to create the csv
	 * file
	 * 
	 * @throws CsvRequiredFieldEmptyException
	 * @throws CsvDataTypeMismatchException
	 */
	public List<FinalCritExtDTO> generateDataForCsvFile() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

		List<MigrateTestDTO> inputList = DOMAIN_MAPPER.mapMigrateTestDto(repository.findAll());

		List<FinalCritExtDTO> list = new ArrayList<>();

		list.addAll(generateCritGuList(inputList));
		logger.info("List generated for vehcat GU ");

		Predicate<MigrateTestDTO> predicate = x -> x.getVehcat().equalsIgnoreCase("CI") && x.getGiociacpt() != null
				&& x.getGiocirule() != null;
		list.addAll(inputList.stream().filter(predicate).map(x -> DOMAIN_MAPPER.mapFinalCritExtCi(x))
				.collect(Collectors.toList()));
		
		logger.info("List generated for vehcat CI ");

		list.addAll(generateCritBkList(inputList));
		logger.info("List generated for vehcat BK, before final filtering ");
		

		list = list.stream().filter(x -> !(x.getNvic().startsWith("!") && x.getVehcat().equalsIgnoreCase("CI")))
				.sorted(Comparator.comparing(FinalCritExtDTO::getVehcat).thenComparing(FinalCritExtDTO::getCompany)
						.thenComparing(FinalCritExtDTO::getNvic))
				.map(x -> {	x.setAddmod("ADD");
					return x;
				}).collect(Collectors.toList());

		return list;

	}

	/**
	 * 
	 * This method servers as creating list from vehcat GU
	 * 
	 * @param inputList dto object list fetched from migrate test table
	 * @return
	 */
	private List<FinalCritExtDTO> generateCritGuList(List<MigrateTestDTO> inputList) {

		List<FinalCritExtDTO> listCritGu = new ArrayList<>();

		inputList.stream().filter(x -> x.getVehcat().equalsIgnoreCase("GU")).forEach(x -> {
			if (x.getAamacpt() != null && x.getAamrule() != null) {
				listCritGu.add(DOMAIN_MAPPER.mapFinalCritExt(x, "AAMI", x.getAamacpt(), x.getAamrule()));
			}

			if (x.getApiacpt() != null && x.getApirule() != null) {
				listCritGu.add(DOMAIN_MAPPER.mapFinalCritExt(x, "APIA", x.getApiacpt(), x.getApirule()));
			}

			if (x.getSunacpt() != null && x.getSunrule() != null) {
				listCritGu.add(DOMAIN_MAPPER.mapFinalCritExt(x, "SUNCORP", x.getSunacpt(), x.getSunrule()));
			}

			if (x.getV03acpt() != null && x.getV03rule() != null) {
				listCritGu.add(DOMAIN_MAPPER.mapFinalCritExt(x, "VERO3", x.getV03acpt(), x.getV03rule()));
			}

			if (x.getV05acpt() != null && x.getV05acpt() != null) {
				listCritGu.add(DOMAIN_MAPPER.mapFinalCritExt(x, "VERO5", x.getV05acpt(), x.getV05acpt()));
			}

			if (x.getGioacpt() != null && x.getGiorule() != null) {
				listCritGu.add(DOMAIN_MAPPER.mapFinalCritExt(x, "GIO", x.getGioacpt(), x.getGiorule()));
			}

			if (x.getEssacpt() != null && x.getEssrule() != null) {
				listCritGu.add(DOMAIN_MAPPER.mapFinalCritExt(x, "ESSENTIALS", x.getEssacpt(), x.getEssrule()));
			}

			if (x.getBingleacpt() != null && x.getBinglerule() != null) {
				listCritGu.add(DOMAIN_MAPPER.mapFinalCritExt(x, "BINGLE", x.getBingleacpt(), x.getBinglerule()));
			}

			if (x.getGiociacpt() != null && x.getGiocirule() != null) {
				listCritGu.add(DOMAIN_MAPPER.mapFinalCritExt(x, "GIOCI", x.getGioacpt(), x.getGiocirule()));
			}

			if (x.getJciacpt() != null && x.getJcirule() != null) {
				listCritGu.add(DOMAIN_MAPPER.mapFinalCritExt(x, "JCI", x.getJciacpt(), x.getJcirule()));
			}

			if (x.getShnacpt() != null && x.getShnrule() != null) {
				listCritGu.add(DOMAIN_MAPPER.mapFinalCritExt(x, "SHANNONS", x.getShnacpt(), x.getShnrule()));
			}
			if (x.getAmpacpt() != null && x.getAmprule() != null) {
				listCritGu.add(DOMAIN_MAPPER.mapFinalCritExt(x, "AMP", x.getAmpacpt(), x.getAmprule()));
			}

		});
		return listCritGu;
	}

	/**
	 * 
	 * This method servers as creating list from vehcat BK
	 * 
	 * @param inputList dto object list fetched from migrate test table
	 * @return
	 */
	private List<FinalCritExtDTO> generateCritBkList(List<MigrateTestDTO> inputList) {

		List<FinalCritExtDTO> listCritBk = new ArrayList<>();

		inputList.stream().filter(x -> x.getVehcat().equalsIgnoreCase("BK")).forEach(x -> {
			if (x.getAamacpt() != null && x.getAamrule() != null) {
				listCritBk.add(DOMAIN_MAPPER.mapFinalCritExt(x, "AAMI", x.getAamacpt(), x.getAamrule()));
			}

			if (x.getApiacpt() != null && x.getApirule() != null) {
				listCritBk.add(DOMAIN_MAPPER.mapFinalCritExt(x, "APIA", x.getApiacpt(), x.getApirule()));
			}

			if (x.getSunacpt() != null && x.getSunrule() != null) {
				listCritBk.add(DOMAIN_MAPPER.mapFinalCritExt(x, "SUNCORP", x.getSunacpt(), x.getSunrule()));
			}

			if (x.getGioacpt() != null && x.getGiorule() != null) {
				listCritBk.add(DOMAIN_MAPPER.mapFinalCritExt(x, "GIO", x.getGioacpt(), x.getGiorule()));
			}

			if (x.getShnacpt() != null && x.getShnrule() != null) {
				listCritBk.add(DOMAIN_MAPPER.mapFinalCritExt(x, "SHANNONS", x.getShnacpt(), x.getShnrule()));
			}

			if (x.getAmpacpt() != null && x.getAmprule() != null) {
				listCritBk.add(DOMAIN_MAPPER.mapFinalCritExt(x, "AMP", x.getGioacpt(), x.getAmprule()));
			}

			if (x.getImracpt() != null && x.getImrrule() != null) {
				listCritBk.add(DOMAIN_MAPPER.mapFinalCritExt(x, "IMR", x.getImracpt(), x.getImrrule()));
			}

		});

		return listCritBk;

	}

}
