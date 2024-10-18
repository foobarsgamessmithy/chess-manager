package eu.foobarssgamesmithy.chessmanager.core.mapper;

import eu.foobarssgamesmithy.chessmanager.core.MatchBo;
import eu.foobarssgamesmithy.chessmanager.service.dto.MatchDto;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-18T20:09:24+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.4 (Eclipse Adoptium)"
)
@Component
public class DtoBoMapperImpl implements DtoBoMapper {

    @Override
    public MatchDto mapMatch(MatchBo match) {
        if ( match == null ) {
            return null;
        }

        MatchDto.MatchDtoBuilder matchDto = MatchDto.builder();

        if ( match.getPlayedAt() != null ) {
            matchDto.playedAt( DateTimeFormatter.ISO_DATE_TIME.format( match.getPlayedAt() ) );
        }
        matchDto.id( match.getId() );

        return matchDto.build();
    }

    @Override
    public MatchBo mapMatch(MatchDto match) {
        if ( match == null ) {
            return null;
        }

        MatchBo.MatchBoBuilder matchBo = MatchBo.builder();

        matchBo.id( match.getId() );
        if ( match.getPlayedAt() != null ) {
            matchBo.playedAt( ZonedDateTime.parse( match.getPlayedAt() ) );
        }

        return matchBo.build();
    }
}
