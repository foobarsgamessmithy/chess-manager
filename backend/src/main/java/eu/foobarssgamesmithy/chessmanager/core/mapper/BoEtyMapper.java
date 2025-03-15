package eu.foobarssgamesmithy.chessmanager.core.mapper;

import eu.foobarssgamesmithy.chessmanager.core.match.data.Castle;
import eu.foobarssgamesmithy.chessmanager.core.match.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.core.match.data.Notion;
import eu.foobarssgamesmithy.chessmanager.core.match.data.Promotion;
import eu.foobarssgamesmithy.chessmanager.core.user.data.UserBo;
import eu.foobarssgamesmithy.chessmanager.persistence.match.entity.MatchEntity;
import eu.foobarssgamesmithy.chessmanager.persistence.match.entity.MoveEntity;
import eu.foobarssgamesmithy.chessmanager.persistence.user.entity.UserEntity;
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
    @Mapping(source = "user", target = "user")
    MatchBo mapMatch(MatchEntity source);

    @Mapping(source = "id", target = "matchId")
    @Mapping(source = "playedAt", target = "playedAt")
    @Mapping(source = "user", target = "user")
    MatchEntity mapMatch(MatchBo source);

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

    List<MatchEntity> mapMatchesToEntity(List<MatchBo> list);

    default MoveEntity map(String source){
        // TODO throw invalid exception
        MoveEntity move = MoveEntity.builder().build();
        if(source.equals(Notion.SHORT_CASTLE)) {
            move.setCastle(Castle.SHORT);
        } else if (source.equals(Notion.LONG_CASTLE)) {
            move.setCastle(Castle.LONG);
        } else {
            for (int i = 0; i < source.length(); i++) {
                String ch = String.valueOf(source.charAt(i));
                if (Notion.PIECES.contains(ch)) {
                    if(i != 0){
                        move.setPromotion(Promotion.fromNotation(ch));
                    } else {
                        if (source.length() > i + 2
                                && Notion.FILES.contains(String.valueOf(source.charAt(i+1)))
                                && Notion.CAPTURE.equals(String.valueOf(source.charAt(i+2)))
                        ) {
                            move.setFile(String.valueOf(source.charAt(i+1)));
                            move.setHasCaptured(true);
                            move.setFigure(ch);
                            i+=2;
                        } else if (source.length() > i + 1
                                && Notion.RANKS.contains(String.valueOf(source.charAt(i+1)))) {
                            move.setRank(String.valueOf(source.charAt(i+1)));
                            move.setFigure(ch);
                            i++;
                        } else {
                            move.setFigure(ch);
                        }
                    }
                } else if (Notion.MADE.equals(ch)) {
                    move.setMade(true);
                } else if (Notion.CAPTURE.equals(ch)) {
                    move.setHasCaptured(true);
                } else if (Notion.CHECK.equals(ch)) {
                    move.setCheck(true);
                } else if(Notion.FILES.contains(ch)){
                    // can be part of destination field or is moved pawn
                    if (source.length() > i + 1) {
                        String nextChar = String.valueOf(source.charAt(i + 1));
                        if(Notion.CAPTURE.equals(nextChar)) {
                            move.setHasCaptured(true);
                            move.setPawn(ch);
                        } else if (nextChar.equals(".")){
                            move.setEnPassant(true);
                            i+=2;
                        } else if(Notion.RANKS.contains(nextChar)){
                            move.setField(ch + nextChar);
                        } else {
                            move.setFile(ch);
                            i--;
                        }
                        i++;
                    }
                }
            }
        }
        return move;
    }

    default String map(MoveEntity source){
        StringBuilder move = new StringBuilder();
        if(source.getPawn() != null){
            move.append(source.getPawn());
        }
        if(source.getFigure() != null){
            move.append(source.getFigure());
        }
        if(source.getFile() != null){
            move.append(source.getFile());
        }
        if(source.getRank() != null){
            move.append(source.getRank());
        }
        if(source.isHasCaptured()){
            move.append(Notion.CAPTURE);
        }
        if(source.getField() != null) {
            move.append(source.getField());
        }
        if(source.isMade()){
            move.append(Notion.MADE);
        }
        if(source.isEnPassant()) {
            move.append(" " + Notion.EN_PASSANT);
        }
        if(source.getPromotion() != null){
            move.append(source.getPromotion().getShortName());
        }
        if(source.isCheck()){
            move.append(Notion.CHECK);
        }
        if(source.getCastle() != null) {
            Castle castle = source.getCastle();
            if(castle.equals(Castle.SHORT)) {
                move.append(Notion.SHORT_CASTLE);
            } else {
                move.append(Notion.LONG_CASTLE);
            }
        }
        return move.toString();
    }

    UserBo mapUser(UserEntity source);

    UserEntity mapUser(UserBo source);
}
