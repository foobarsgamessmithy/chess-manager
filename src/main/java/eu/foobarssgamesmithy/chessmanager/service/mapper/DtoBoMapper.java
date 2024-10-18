package eu.foobarssgamesmithy.chessmanager.service.mapper;

import eu.foobarssgamesmithy.chessmanager.core.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.service.dto.MatchDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DtoBoMapper {

    @Mapping(target = "playedAt", source = "playedAt")
    MatchDto mapMatch(MatchBo match);

    @Mapping(target = "playedAt", source = "playedAt")
    MatchBo mapMatch(MatchDto match);

}
