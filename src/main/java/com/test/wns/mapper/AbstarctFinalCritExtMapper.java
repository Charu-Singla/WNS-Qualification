package com.test.wns.mapper;

import com.test.wns.dto.FinalCritExtDTO;
import com.test.wns.model.FinalCritExt;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class AbstarctFinalCritExtMapper {
	
	
	/**
	 * The AbstarctFinalCritExtMapper INSTANCE.
	 */
	public static final AbstarctFinalCritExtMapper INSTANCE = Mappers.getMapper(AbstarctFinalCritExtMapper.class);

	/**
	 * Map domain entity object to domain tranfer object.
	 *
	 * @param list of entity objects
	 * @return list of dto
	 */
	@IterableMapping(qualifiedByName = "mapObjects")
	public abstract List<FinalCritExtDTO> mapFinalCritExtDTOList(
			List<FinalCritExt> entity);
	
	/**
	 * @param entity
	 * @return dto object
	 */
	@Named("mapObjects")
	@Mapping(source = "pk.company", target = "company")
	@Mapping(source = "pk.nvic", target = "nvic")
	@Mapping(source = "pk.vehcat", target = "vehcat")
	public abstract FinalCritExtDTO mapFinalCritExtDTO(
			FinalCritExt entity);
	

}
