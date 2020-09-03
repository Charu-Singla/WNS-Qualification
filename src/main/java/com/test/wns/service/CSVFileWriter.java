package com.test.wns.service;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.test.wns.dto.FinalCritExtDTO;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class CSVFileWriter {

	public void writeDataAtOnce(List<FinalCritExtDTO> data)
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

		try {
			FileWriter writer = new FileWriter("FINAL_CRIT_EXT.csv");
			ColumnPositionMappingStrategy<FinalCritExtDTO> mappingStrategy = new ColumnPositionMappingStrategy<>();
			mappingStrategy.setType(FinalCritExtDTO.class);

			 // Arrange column name as provided in below array. 
            String[] columns = new String[]  
                    { "addmod","company", "nvic", "vehcat", "effectivedate", "changetimestamp", "effectiveenddate", "enddatetimestamp", "acceptcrit" , "internetjep"}; 
            mappingStrategy.setColumnMapping(columns); 
			
			StatefulBeanToCsvBuilder<FinalCritExtDTO> builder = new StatefulBeanToCsvBuilder<>(writer);
			StatefulBeanToCsv<FinalCritExtDTO> beanWriter = builder.withMappingStrategy(mappingStrategy).build();

			// Write list to StatefulBeanToCsv object
			beanWriter.write(data);

			// closing writer connection
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
