package eu.foobarssgamesmithy.chessmanager.core.mapper;

import eu.foobarssgamesmithy.chessmanager.core.MatchBo;
import eu.foobarssgamesmithy.chessmanager.service.dto.MatchDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DtoBoMapper {

    @Mapping(target = "playedAt", source = "playedAt")
    @Mapping(target = "id", source = "id")
    MatchDto mapMatch(MatchBo match);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "playedAt", source = "playedAt")
    MatchBo mapMatch(MatchDto match);

}
