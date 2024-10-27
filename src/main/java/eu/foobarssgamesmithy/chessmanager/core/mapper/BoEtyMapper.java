package eu.foobarssgamesmithy.chessmanager.core.mapper;

import eu.foobarssgamesmithy.chessmanager.core.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.persistence.entity.MatchEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoEtyMapper {

    @Mapping(source = "matchId", target = "id")
    @Mapping(source = "playedAt", target = "playedAt")
    MatchBo mapMatch(MatchEntity match);

    @Mapping(source = "id", target = "matchId")
    @Mapping(source = "playedAt", target = "playedAt")
    MatchEntity mapMatch(MatchBo match);
}
