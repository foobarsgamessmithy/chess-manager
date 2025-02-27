package eu.foobarssgamesmithy.chessmanager.service.mapper;

import eu.foobarssgamesmithy.chessmanager.core.match.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.core.user.data.UserBo;
import eu.foobarssgamesmithy.chessmanager.service.match.data.MatchDto;
import eu.foobarssgamesmithy.chessmanager.service.user.data.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DtoBoMapper {

    MatchDto mapMatch(MatchBo match);

    @Mapping(target = "result.id", ignore = true)
    @Mapping(target = "user", ignore = true)
    MatchBo mapMatch(MatchDto match);

    default String map(ZonedDateTime zonedDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        return formatter.format(zonedDateTime);
    }

    List<MatchDto> mapMatches(List<MatchBo> matches);

    @Mapping(target = "lichessUsername", ignore = true)
    UserDto mapUser(UserBo user);

    @Mapping(target = "id", ignore = true)
    UserBo mapUser(UserDto user);
}
