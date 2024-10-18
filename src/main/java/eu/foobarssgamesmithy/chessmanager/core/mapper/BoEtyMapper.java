package eu.foobarssgamesmithy.chessmanager.core.mapper;

import eu.foobarssgamesmithy.chessmanager.core.MatchBo;
import eu.foobarssgamesmithy.chessmanager.persistence.entity.MatchEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BoEtyMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "playedAt", target = "playedAt")
    MatchBo mapMatch(MatchEntity match);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "playedAt", target = "playedAt")
    MatchEntity mapMatch(MatchBo match);
}
