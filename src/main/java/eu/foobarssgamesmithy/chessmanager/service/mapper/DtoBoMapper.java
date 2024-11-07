package eu.foobarssgamesmithy.chessmanager.service.mapper;

import eu.foobarssgamesmithy.chessmanager.core.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.service.dto.MatchDto;
import org.mapstruct.Mapper;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DtoBoMapper {

    MatchDto mapMatch(MatchBo match);

    MatchBo mapMatch(MatchDto match);

    default String map(ZonedDateTime zonedDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        return formatter.format(zonedDateTime);
    }

    List<MatchDto> mapMatches(List<MatchBo> matches);
}
