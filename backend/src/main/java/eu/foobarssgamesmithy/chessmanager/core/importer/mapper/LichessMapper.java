package eu.foobarssgamesmithy.chessmanager.core.importer.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.foobarssgamesmithy.chessmanager.core.importer.data.LichessMatchDto;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring")
public interface LichessMapper {

    default LichessMatchDto mapMatch(String match) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(match, LichessMatchDto.class);
    }

    default List<LichessMatchDto> mapMatchList(String match) {
        List<LichessMatchDto> matchList = new ArrayList<>();
        List<String> stringList = Arrays.stream(match.split("\n")).toList();
        stringList.forEach(m -> {
            try {
                matchList.add(mapMatch(m));
            } catch (JsonProcessingException e) {
                // TODO
                throw new RuntimeException(e);
            }
        });
        return matchList;
    }

}
