package com.test.wns.mapper;

import com.test.wns.dto.FinalCritExtDTO;
import com.test.wns.dto.MigrateTestDTO;
import com.test.wns.model.MigrateTest;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper
public abstract class AbstarctFinalCritExtMapper {

	/**
	 * The AbstarctFinalCritExtMapper INSTANCE.
	 */
	public static final AbstarctFinalCritExtMapper INSTANCE = Mappers.getMapper(AbstarctFinalCritExtMapper.class);

	/**
	 * 
	 * List mapping from enity fetched from db to dto list
	 * 
	 * @param list List<MigrateTest>
	 * @return
	 */
	@IterableMapping(qualifiedByName = "mapMigrateTestObjects")
	public abstract List<MigrateTestDTO> mapMigrateTestDto(List<MigrateTest> list);

	/**
	 * 
	 * This map entity object into data transfer object wit additional fields for
	 * dates
	 * 
	 * @param entity
	 * @return MigrateTestDTO
	 */
	@Named("mapMigrateTestObjects")
	@Mapping(target = "effectivedate", expression = "java(getEffectiveDate())")
	@Mapping(target = "changetimestamp", expression = "java(getChangeTimeStamp())")
	@Mapping(target = "effectiveenddate", constant = "99991231")
	@Mapping(target = "enddatetimestamp", constant = "99991231000000")
	public abstract MigrateTestDTO mapMigrateTestDtoObject(MigrateTest entity);

	/**
	 * 
	 * This is mapping method from fetched data from db to final expected object
	 * with additional attributes company , accept crit, internetjep
	 * 
	 * @param MigrateTestDTO
	 * @return FinalCritExtDTO
	 */
	@Mapping(target = "company", constant = "GIOCI")
	@Mapping(target = "acceptcrit", source = "giociacpt")
	@Mapping(target = "internetjep", source = "giocirule")
	public abstract FinalCritExtDTO mapFinalCritExtCi(MigrateTestDTO entity);

	/**
	 * 
	 * This is general common method to map into final crit ext object
	 * 
	 * @param input     entity
	 * @param commpany
	 * @param inputCpt
	 * @param inputRule
	 * @return FinalCritExtDTO
	 */
	@Mapping(target = "company", source = "commpany")
	@Mapping(target = "acceptcrit", source = "inputCpt")
	@Mapping(target = "internetjep", source = "inputRule")
	public abstract FinalCritExtDTO mapFinalCritExt(MigrateTestDTO input, String commpany, String inputCpt,
			String inputRule);

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
