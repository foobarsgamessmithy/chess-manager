package eu.foobarssgamesmithy.chessmanager.service.mapper;

import eu.foobarssgamesmithy.chessmanager.core.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.service.data.MatchDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DtoBoMapper {

    MatchDto mapMatch(MatchBo match);

    @Mapping(target = "result.id", ignore = true)
    MatchBo mapMatch(MatchDto match);

    default String map(ZonedDateTime zonedDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        return formatter.format(zonedDateTime);
    }

    List<MatchDto> mapMatches(List<MatchBo> matches);
}
