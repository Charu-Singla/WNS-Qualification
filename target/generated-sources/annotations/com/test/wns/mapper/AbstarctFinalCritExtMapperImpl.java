package com.test.wns.mapper;

import com.test.wns.dto.FinalCritExtDTO;
import com.test.wns.model.EmbeddedPrimaryKey;
import com.test.wns.model.FinalCritExt;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-03T18:12:03+0530",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 11.0.3 (Oracle Corporation)"
)
public class AbstarctFinalCritExtMapperImpl extends AbstarctFinalCritExtMapper {

    @Override
    public List<FinalCritExtDTO> mapFinalCritExtDTOList(List<FinalCritExt> entity) {
        if ( entity == null ) {
            return null;
        }

        List<FinalCritExtDTO> list = new ArrayList<FinalCritExtDTO>( entity.size() );
        for ( FinalCritExt finalCritExt : entity ) {
            list.add( mapFinalCritExtDTO( finalCritExt ) );
        }

        return list;
    }

    @Override
    public FinalCritExtDTO mapFinalCritExtDTO(FinalCritExt entity) {
        if ( entity == null ) {
            return null;
        }

        FinalCritExtDTO finalCritExtDTO = new FinalCritExtDTO();

        String nvic = entityPkNvic( entity );
        if ( nvic != null ) {
            finalCritExtDTO.setNvic( nvic );
        }
        String company = entityPkCompany( entity );
        if ( company != null ) {
            finalCritExtDTO.setCompany( company );
        }
        String vehcat = entityPkVehcat( entity );
        if ( vehcat != null ) {
            finalCritExtDTO.setVehcat( vehcat );
        }
        finalCritExtDTO.setAddmod( entity.getAddmod() );
        finalCritExtDTO.setEffectivedate( entity.getEffectivedate() );
        finalCritExtDTO.setChangetimestamp( entity.getChangetimestamp() );
        finalCritExtDTO.setEffectiveenddate( entity.getEffectiveenddate() );
        finalCritExtDTO.setEnddatetimestamp( entity.getEnddatetimestamp() );
        finalCritExtDTO.setAcceptcrit( entity.getAcceptcrit() );
        finalCritExtDTO.setInternetjep( entity.getInternetjep() );

        return finalCritExtDTO;
    }

    private String entityPkNvic(FinalCritExt finalCritExt) {
        if ( finalCritExt == null ) {
            return null;
        }
        EmbeddedPrimaryKey pk = finalCritExt.getPk();
        if ( pk == null ) {
            return null;
        }
        String nvic = pk.getNvic();
        if ( nvic == null ) {
            return null;
        }
        return nvic;
    }

    private String entityPkCompany(FinalCritExt finalCritExt) {
        if ( finalCritExt == null ) {
            return null;
        }
        EmbeddedPrimaryKey pk = finalCritExt.getPk();
        if ( pk == null ) {
            return null;
        }
        String company = pk.getCompany();
        if ( company == null ) {
            return null;
        }
        return company;
    }

    private String entityPkVehcat(FinalCritExt finalCritExt) {
        if ( finalCritExt == null ) {
            return null;
        }
        EmbeddedPrimaryKey pk = finalCritExt.getPk();
        if ( pk == null ) {
            return null;
        }
        String vehcat = pk.getVehcat();
        if ( vehcat == null ) {
            return null;
        }
        return vehcat;
    }
}
