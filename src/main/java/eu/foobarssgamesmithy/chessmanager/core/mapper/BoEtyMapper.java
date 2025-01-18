package eu.foobarssgamesmithy.chessmanager.core.mapper;

import eu.foobarssgamesmithy.chessmanager.core.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.core.data.Notion;
import eu.foobarssgamesmithy.chessmanager.persistence.entity.MatchEntity;
import eu.foobarssgamesmithy.chessmanager.persistence.entity.MoveEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoEtyMapper {

    @Mapping(source = "matchId", target = "id")
    @Mapping(source = "playedAt", target = "playedAt")
    MatchBo mapMatch(MatchEntity match);

    @Mapping(source = "id", target = "matchId")
    @Mapping(source = "playedAt", target = "playedAt")
    MatchEntity mapMatch(MatchBo match);

    default List<MoveEntity> mapToMoveEntityList(List<String> moves) {
        List<MoveEntity> list = new ArrayList<>();
        moves.forEach( m -> {
            Arrays.stream(m.split(" ")).forEach( sm -> {
                list.add(map(sm));
            });
        });
        return list;
    }

    default List<String> mapToMoveStringList(List<MoveEntity> source){
        List<String> moves = new ArrayList<>();
        for (int i = 0; i < source.size(); i+=2){
            StringBuilder move = new StringBuilder(map(source.get(i)));
            if(source.size() > i +1){
                move.append(" " + map(source.get(i+1)));
            }
            moves.add(move.toString());
        }
        return moves;
    }

    List<MatchBo> mapMatches(List<MatchEntity> list);

    default MoveEntity map(String source){
        MoveEntity move = MoveEntity.builder().build();
        for(int i = 0; i < source.length(); i++){
            Character ch = source.charAt(i);
            if(Character.isUpperCase(ch)){
                move.setFigure(String.valueOf(ch));
            } else if(Notion.MADE.equals(String.valueOf(ch))){
                move.setMade(true);
            }else if(Notion.CAPTURE.equals(String.valueOf(ch))) {
                move.setHasCaptured(true);
            } else {
                StringBuilder field = new StringBuilder(String.valueOf(ch));
                if(source.length() > i+1){
                    field.append(source.charAt(i + 1));
                    i++;
                }
                move.setField(field.toString());
            }
        }
        return move;
    }

    default String map(MoveEntity source){
        StringBuilder move = new StringBuilder();
        if(source.getFigure() != null){
            move.append(source.getFigure());
        }
        if(source.isHasCaptured()){
            move.append(Notion.CAPTURE);
        }
        move.append(source.getField());
        if(source.isMade()){
            move.append(Notion.MADE);
        }
        return move.toString();
    }
}
